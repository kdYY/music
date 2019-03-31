import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.ebook.user.dao.*;
import com.ebook.user.model.*;
import com.ebook.user.service.AnalysisService;
import com.ebook.user.service.MusicService;
import com.ebook.util.pageHelper.PageBean;
import com.github.pagehelper.PageInfo;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.ebook.SpringBootDemoApplication.class)
public class SpringBootDemoApplicationTest {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TaginsonglistMapper taginsonglistMapper;
   @Autowired
    private SonglistMapper songlistMapper;
   @Autowired
   private SongMapper songMapper;
   @Autowired
   private SongtolistMapper songtolistMapper;
   @Autowired
    private AnalysisService analysisService;
   @Autowired
   private MusicService musicService;

    @Test
    public void test() {
        try {
            for (int i = 6; i < 36; i++) {
                TaginsonglistExample taginsonglistExample = new TaginsonglistExample();
                TaginsonglistExample.Criteria criteria = taginsonglistExample.createCriteria();
                criteria.andTagidEqualTo(i);
                SonglistExample songlistExample = new SonglistExample();
                SonglistExample.Criteria criteria1 = songlistExample.createCriteria();
                criteria1.andSonglistidIsNotNull();
                List<Songlist> songlists = songlistMapper.selectByExample(songlistExample);
                List<String> collect = songlists.parallelStream().map(Songlist::getSonglistid).collect(Collectors.toList());
                criteria.andSonglistidNotIn(collect);
                List<Taginsonglist> taginsonglists = taginsonglistMapper.selectByExample(taginsonglistExample);

                taginsonglists.forEach(value -> {
                    try {
                        //System.out.println(value.toString());
                        URL url = new URL("https://api.bzqll.com/music/netease/songList?key=579621905&id=" + value.getSonglistid() + "&limit=35&offset=0");
                        String songListJson = GetAllSongs.getURLSource(url);
                        JsonParser parse = new JsonParser();  //创建json解析器
                        JsonObject json = (JsonObject) parse.parse(songListJson);  //创建jsonObject对象
                        JsonObject data = json.getAsJsonObject("data");
                        if(data == null)
                            return;
                        int songNum = 0;
                        SonglistWithBLOBs songlist = new SonglistWithBLOBs();
                        try {
                            songlist.setSonglistid(data.get("songListId").getAsString());
                            songlist.setSonglistname(data.get("songListName").getAsString());
                            songlist.setSonglistcount(data.get("songListCount").getAsInt());
                            songlist.setSonglistplaycount(data.get("songListPlayCount").getAsLong());
                            songlist.setSonglistpic(data.get("songListPic").getAsString());
                            songlist.setSonglistdescription(data.get("songListDescription").getAsString());
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        //System.out.println(songlist.toString());
                        try {
                            songlistMapper.insert(songlist);
                        } catch (Exception e) {
                            System.out.println(songlist.toString() + "插入失败");
                            e.printStackTrace();
                        }

                        JsonArray sub = data.getAsJsonArray("songs");
                        List<Song> songs = new ArrayList<>();
                        List<Songtolist> songtolists = new ArrayList<>();
                        for (JsonElement element : sub) {
                            Song song = new Song();
                            JsonObject asJsonObject = element.getAsJsonObject();
                            if(asJsonObject != null) {
                                song.setSongid(asJsonObject.get("id").getAsString());
                                song.setName(asJsonObject.get("name").getAsString());
                                song.setSinger(asJsonObject.get("singer").getAsString());
                                song.setPic(asJsonObject.get("pic").getAsString());
                                String lrc = GetAllSongs.getURLSource(new URL(asJsonObject.get("lrc").getAsString()));
                                song.setLrc(lrc);
                                song.setUrl("http://music.163.com/song/media/outer/url?id=" + song.getSongid());
                                song.setTime(asJsonObject.get("time").getAsInt());
                                songs.add(song);

                                Songtolist songtolist = new Songtolist();
                                songtolist.setSongid(song.getSongid());
                                songtolist.setSonglistid(songlist.getSonglistid());

                                songtolists.add(songtolist);

                                if(songs.size() == 100) {
                                    try {
                                        songMapper.insertBatch(songs);
                                        songNum += 100;

                                    } catch (Exception e) {
                                        songs.forEach(v->{
                                            System.out.println(v.toString());
                                        });
                                        System.out.println("songs批量插入失败");
                                        e.printStackTrace();
                                    } finally {
                                        songs.clear();
                                    }
                                    try {
                                        songtolistMapper.insertBatch(songtolists);
                                        break;
                                    } catch (Exception e) {
                                        songtolists.forEach(v->{
                                            System.out.println(v.toString());
                                        });
                                        System.out.println("songtolists批量插入失败");
                                        e.printStackTrace();
                                    } finally {
                                        songtolists.clear();
                                    }

                                }
                            }

                        }
                        if(songs.size() != 0) {
                            try {
                                songMapper.insertBatch(songs);
                                songtolistMapper.insertBatch(songtolists);
                                songNum += songs.size();
                            }  catch (Exception e) {
                                songtolists.forEach(v->{
                                    System.out.println(v.toString());
                                });
                                System.out.println("插入失败");
                                e.printStackTrace();
                            } finally {
                                songs.clear();
                                songtolists.clear();
                            }
                        }

                        System.out.println("总共有" + songNum + "首歌曲插入成功");



                    } catch (MalformedURLException e) {
                        System.out.println("获取异常, 歌单id="+ value.getSonglistid());
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.out.println("获取异常, 歌单id="+ value.getSonglistid());
                        e.printStackTrace();
                    }

                });


            }
            //tagMapper.insertBatch(tags);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test1() {
        JsonParser parse = new JsonParser();  //创建json解析器
        boolean flag = false;
        try {
            JsonObject json = (JsonObject) parse.parse(new FileReader("C:\\Users\\LYWCE\\Desktop\\tag.json"));  //创建jsonObject对象
            JsonArray sub = json.getAsJsonArray("sub");
            for (JsonElement element : sub) {
                JsonObject asJsonObject = element.getAsJsonObject();
                JsonElement name = asJsonObject.get("name");
                if (name != null) {
                    if (name.getAsString().equals("80后") || flag) {
                        flag = true;
                        System.out.println("name=" + name + ";");
                        getSongList(name.getAsString(), "\"/playlist\\?id=(\\d+)?\"", null);
                    }
                }
            }

            //tagMapper.insertBatch(tags);

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        musicService.innerJobBefore();
    }

    private void getSongList(String name, String regex, URL url) {
        Set<String> fail = new HashSet<>();
        for(int i=0; i< 37; i++) {

            try {
                url = new URL("https://music.163.com/discover/playlist/?cat=" + name + "&limit=35&offset=" + i * 35);

                List<Taginsonglist> taginsonglists = new ArrayList<>();
               String urlsource = GetAllSongs.getURLSource(url);
                String song_regex = regex;
                //System.out.println(urlsource);
                // 创建Pattern对象
                Pattern songIdPattern = Pattern.compile(song_regex);

                Matcher matcher = songIdPattern.matcher(urlsource);

                while(matcher.find()) {
                    Taginsonglist tagsong = new Taginsonglist();
                    tagsong.setTagid(tagMapper.selectByName(name).getId());
                    String songListId = "";
                    try {
                        songListId =matcher.group().split("id=")[1].split("\"")[0];
                    } catch (Exception e) {
                        songListId = "";
                        System.out.println("get songListId err word=" + matcher.group() + e.getMessage());
                    }
                    tagsong.setSonglistid(songListId);
                    //System.out.println(tagsong.toString());
                    taginsonglists.add(tagsong);
                }
                taginsonglistMapper.insertBatch(taginsonglists);
                Thread.currentThread().sleep(new Random().nextInt(1000) + 800);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("解析失败, name=" + name + ";offset=" + i);
                fail.add(name);
                try {
                    Thread.currentThread().sleep(new Random().nextInt(1000) + 800);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(fail.size() != 0) {
            for (String s : fail) {
                System.out.print("fail:{" + s + ",");
            }
            System.out.println("}");
        }


    }

    static class GetAllSongs extends BreadthCrawler {

        // private CsvWriter r = null;

        public void closeCsv() {
            // this.r.close();
        }
        /**
         * 转换字节流
         * @param instream
         * @return
         * @throws IOException
         */
        public static byte[] readInputStream(InputStream instream) throws IOException {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[]  buffer = new byte[1204];
            int len = 0;
            while ((len = instream.read(buffer)) != -1){
                outStream.write(buffer,0,len);
            }
            instream.close();
            return outStream.toByteArray();
        }
        /**
         * 根据URL获得网页源码
         * @param url 传入的URL
         * @return String
         * @throws IOException
         */
        public static String getURLSource(URL url) {
            HttpURLConnection conn = null;
            String htmlSource = null;
            byte[] data;
            boolean flag = false;
            int i = 0;
            while(!flag && i < 10) {
                try {
                    //Thread.currentThread().sleep(new Random().nextInt(800));
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestProperty("Cookie", "_iuqxldmzr_=32; _ntes_nnid=b753fd14403222ccc873aa6bc200bc93,1552994288862; _ntes_nuid=b753fd14403222ccc873aa6bc200bc93; WM_TID=N7w%2FE4Qw2wBEFAUFQAcol8JizeRgLhJB; __remember_me=true; WM_NI=lh6ALDBQYbhK%2B0%2F%2F0eBaRutDZmIG2Fzsa%2FGUdICphi0pVZLXk7SxqMnsUkEVINVOv9Yg8zu4MpktXqHupMaWs8XDh%2Fqplk9C%2FnHFD6eymS39zPiPpz2oVC2cceSvZiX4TlM%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee89ec3fbababe9ab14baa868ea2c14e969f8bbbf37db191aba3e6748da7ff82db2af0fea7c3b92ab0b8faa2cf50828f9783eb41e9f0e1d0b36f82bf9e96f7509ab387a8b14ebaadafd0c65fa8978d91eb63f4b09abbcf54a9e99d99f447aead9b8cc85e8b929ab0f5339bb9f89bf8408fb0a595db7b8cabc0aee45db1e898a5eb25b3b0a48cbc49edb288a7c73ea7a7a3b4b233ba90a7b1f372f2b19aa4b169a1bb9d97d9639cec9c8dd037e2a3; JSESSIONID-WYYY=gcJkoi7V2dWEQmi1yDAOJ%5Cd0PHlqBKAG64Zl8Pvko8OUVqFBbxUmTz5UG9sbpBBsVj4D9U4VANvHkOPFoWfb%2Fi%5CAx0%5C8D%2FStrFnKMRjMF4tEWNAUn4gWysZq0lh7PyIHKT9%5CfcqCanWS7iERmHZUGGeQS6EUsTsCRx%2BEYEtc1ccB%2FKfn%3A1553135226202; MUSIC_U=df330d0b2e770abb1ef5e4bac1b714e015ce66934b63b5a6bb4331fd30ab1e40164021fe200f5c5ff920d11a2d0f368631b299d667364ed3; __csrf=ee5d48a541df0777feb8cfbdab1bb2aa");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("csrf_token", "ee5d48a541df0777feb8cfbdab1bb2aa");
                    conn.setConnectTimeout(50000);//时间可以设置的久一点，如果控制台经常提示read time out
                    InputStream inStream = conn.getInputStream();
                    data = readInputStream(inStream);
                    conn.disconnect();
                    htmlSource = new String(data);
                    flag = true;
                } catch (IOException e) {
                    System.out.println("获取连接失败");
                    e.printStackTrace();
                    try {
                        Thread.currentThread().sleep(new Random().nextInt(1000) + 1000);
                        System.out.println("尝试连接了" + i + "次;异常信息为" + e.getMessage());
                        i++;
                        if(i > 5) {
                            Thread.currentThread().sleep(new Random().nextInt(1000) + 4000);
                        }
                    } catch (InterruptedException e1) {
                        continue;
                    }
                }
            }

            //System.out.println(htmlSource);

            return htmlSource;
        }
        /**
         * 重写构造函数
         * @param crawlPath 爬虫路径
         * @param autoParse 是否自动解析
         */
        public GetAllSongs(String crawlPath, boolean autoParse) throws FileNotFoundException {
            super(crawlPath, autoParse);
            // 逗号进行分割，字符编码为GBK
            //this.r = new CsvWriter("songId.csv", ',', Charset.forName("GBK"));
        }

        @Override
        public void visit(Page page, CrawlDatums next) {
            System.out.println("visit===");
            // 继承覆盖visit方法，该方法表示在每个页面进行的操作
            // 参数page和next分别表示当前页面和下个URL对象的地址
            // 生成文件songId.csv，第一列为歌曲id，第二列为歌曲名字，第三列为演唱者，第四列为歌曲信息的URL
            // 网易云音乐song页面URL地址正则
            String song_regex = "^http://music.163.com/song\\?id=[0-9]+";
            // 创建Pattern对象                          http://music.163.com/#/song?id=110411
            Pattern songIdPattern = Pattern.compile("^http://music.163.com/song\\?id=([0-9]+)");
            Pattern songInfoPattern = Pattern.compile("(.*?)-(.*?)-");
            // 对页面进行正则判断，如果有的话，将歌曲的id和网页标题提取出来，否则不进行任何操作
            if (Pattern.matches(song_regex, page.url())) {
                // 将网页的URL和网页标题提取出来，网页标题格式：歌曲名字-歌手-网易云音乐
                String url = page.url();
                @SuppressWarnings("deprecation")
                String title = page.doc().title();
                String songName = null;
                String songSinger = null;
                String songId = null;
                String infoUrl = null;
                String mp3Url = null;
                // 对标题进行歌曲名字、歌手解析
                Matcher infoMatcher = songInfoPattern.matcher(title);
                if (infoMatcher.find()) {
                    songName = infoMatcher.group(1);
                    songSinger = infoMatcher.group(2);
                }
                System.out.println("正在抽取:" + url);
                // 创建Matcher对象，使用正则找出歌曲对应id
                Matcher idMatcher = songIdPattern.matcher(url);
                if (idMatcher.find()) {
                    songId = idMatcher.group(1);
                }
                System.out.println("歌曲:" + songName);
                System.out.println("演唱者:" + songSinger);
                System.out.println("ID:" + songId);
                infoUrl = "http://music.163.com/api/song/detail/?id=" + songId + "&ids=%5B+" + songId + "%5D";
                try {
                    URL urlObject = new URL(infoUrl);
                    // 获取json源码
                    String urlsource = getURLSource(urlObject);
                    JSONObject j = new JSONObject(urlsource);
                    JSONArray a = (JSONArray) j.get("songs");
                    JSONObject aa = (JSONObject) a.get(0);
                    mp3Url = aa.get("mp3Url").toString();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String[] contents = {songId, songName, songSinger, url, mp3Url};

            }
        }
        /**
         * 歌曲id爬虫开始
         * @param args
         * @throws Exception
         */

    }

//    //@Test
//    public void test2() {
////        analysisService.songListEmotionAnalysis();
//        for (int i = 1; i < 5; i++) {
//            TaginsonglistExample taginsonglistExample = new TaginsonglistExample();
//            TaginsonglistExample.Criteria criteria = taginsonglistExample.createCriteria();
//            criteria.andTagidEqualTo(i);
//            List<Taginsonglist> taginsonglists = taginsonglistMapper.selectByExample(taginsonglistExample);
//            songlistNewMapper.insertBatch(taginsonglists);
//        }
//
//        for (int i = 5; i <= 45; i++) {
//            TaginsonglistExample taginsonglistExample = new TaginsonglistExample();
//            TaginsonglistExample.Criteria criteria = taginsonglistExample.createCriteria();
//            criteria.andTagidEqualTo(i);
//            List<Taginsonglist> taginsonglists = taginsonglistMapper.selectByExample(taginsonglistExample);
//            List<Taginsonglist> collect = taginsonglists.stream().sorted((a, b) -> a.getId() - b.getId()).limit(100).collect(Collectors.toList());
//            System.out.println(collect.size());
//            songlistNewMapper.insertBatch(collect);
//
//        }
    //}

    @Test
    public void test3(){
//        musicService.getSongListPage("946216567", 1, 10).getList().forEach(v->{
//            System.out.println(v.toString());
//        });
//        //songlistPageBean.setTotalPages();
        //musicService.getTopPlayCountSongSheet(1,1,10);
        musicService.searchSong("沙漠骆驼", 1, 1, 10);
    }


}
