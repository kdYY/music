package com.ebook.user.controller;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.json.*;

/**
 * 获取网易云音乐所有歌曲写入csv文件
 * @author AoXiang
 */
public class GetAllSongs extends BreadthCrawler {

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
    public static String getURLSource(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestProperty("Cookie", "_iuqxldmzr_=32; _ntes_nnid=b753fd14403222ccc873aa6bc200bc93,1552994288862; _ntes_nuid=b753fd14403222ccc873aa6bc200bc93; WM_TID=N7w%2FE4Qw2wBEFAUFQAcol8JizeRgLhJB; __remember_me=true; WM_NI=lh6ALDBQYbhK%2B0%2F%2F0eBaRutDZmIG2Fzsa%2FGUdICphi0pVZLXk7SxqMnsUkEVINVOv9Yg8zu4MpktXqHupMaWs8XDh%2Fqplk9C%2FnHFD6eymS39zPiPpz2oVC2cceSvZiX4TlM%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee89ec3fbababe9ab14baa868ea2c14e969f8bbbf37db191aba3e6748da7ff82db2af0fea7c3b92ab0b8faa2cf50828f9783eb41e9f0e1d0b36f82bf9e96f7509ab387a8b14ebaadafd0c65fa8978d91eb63f4b09abbcf54a9e99d99f447aead9b8cc85e8b929ab0f5339bb9f89bf8408fb0a595db7b8cabc0aee45db1e898a5eb25b3b0a48cbc49edb288a7c73ea7a7a3b4b233ba90a7b1f372f2b19aa4b169a1bb9d97d9639cec9c8dd037e2a3; JSESSIONID-WYYY=gcJkoi7V2dWEQmi1yDAOJ%5Cd0PHlqBKAG64Zl8Pvko8OUVqFBbxUmTz5UG9sbpBBsVj4D9U4VANvHkOPFoWfb%2Fi%5CAx0%5C8D%2FStrFnKMRjMF4tEWNAUn4gWysZq0lh7PyIHKT9%5CfcqCanWS7iERmHZUGGeQS6EUsTsCRx%2BEYEtc1ccB%2FKfn%3A1553135226202; MUSIC_U=df330d0b2e770abb1ef5e4bac1b714e015ce66934b63b5a6bb4331fd30ab1e40164021fe200f5c5ff920d11a2d0f368631b299d667364ed3; __csrf=ee5d48a541df0777feb8cfbdab1bb2aa");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        conn.setRequestMethod("GET");
        conn.setRequestProperty("csrf_token", "ee5d48a541df0777feb8cfbdab1bb2aa");
        conn.setConnectTimeout(500000);//时间可以设置的久一点，如果控制台经常提示read time out
        System.out.println(conn.getContent());
        InputStream inStream = conn.getInputStream();
        byte[] data = readInputStream(inStream);
        String htmlSource = new String(data);
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
    public static void main(String[] args) throws Exception {



        URL url = new URL("https://music.163.com/discover/playlist/?cat=%E5%8D%8E%E8%AF%AD");
        String urlsource = getURLSource(url);
        System.out.println(urlsource);




        JSONObject j = new JSONObject(urlsource);
        JSONArray a = (JSONArray) j.get("songs");
        JSONObject aa = (JSONObject) a.get(0);
        System.out.println(aa.get("mp3Url"));
        GetAllSongs crawler = new GetAllSongs("crawler", true);
        // 添加初始种子页面http://music.163.com
        crawler.addSeed("http://music.163.com/#/album?id=604667405");
        // 设置采集规则为所有类型的网页
        crawler.addRegex("http://music.163.com/.*");
        // 设置爬取URL数量的上限
        crawler.setMaxExecuteCount(10000);
        // 设置线程数
        crawler.setThreads(30);
        // 设置断点采集
        crawler.setResumable(false);
        // 设置爬虫深度
        crawler.start(5);
    }
}