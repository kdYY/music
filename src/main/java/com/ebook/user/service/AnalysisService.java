package com.ebook.user.service;

import com.ebook.common.AnalysisRunnable;
import com.ebook.common.Singleton;
import com.ebook.user.dao.SongMapper;
import com.ebook.user.dao.SonglistMapper;
import com.ebook.user.dao.SongtolistMapper;
import com.ebook.user.model.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AnalysisService {


    public static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired
    private SonglistMapper songlistMapper;
    @Autowired
    private SongtolistMapper songtolistMapper;
    @Autowired
    private SongMapper songMapper;


    /**
     * 歌词情感倾向分析
     */
    public  void songListEmotionAnalysis() {
        //id小于40000 songListId != 2585795384
        SonglistExample songlistExample = new SonglistExample();
        songlistExample.createCriteria();
        List<Songlist> songlists = songlistMapper.selectByExample(songlistExample);
        songlists.forEach(songlist -> {
            String songListId = songlist.getSonglistid();
            if(!songListId.equals("2585795384")) {
                SongtolistExample songtolistExample = new SongtolistExample();
                SongtolistExample.Criteria criteria = songtolistExample.createCriteria();
                criteria.andSonglistidEqualTo(songListId);
                //criteria.andSongidNotEqualTo("2585795384");

                List<Songtolist> songIdlist = songtolistMapper.selectByExample(songtolistExample);
                if(songIdlist.size() > 0) {
                    List<String> collectSongid = songIdlist.stream().map(Songtolist::getSongid).collect(Collectors.toList());
                    SongExample songExample = new SongExample();
                    SongExample.Criteria criteria1 = songExample.createCriteria();
                    criteria1.andSongidIn(collectSongid);
                    criteria1.andIdLessThan(40000);
                    List<Song> songs = songMapper.selectByExampleWithBLOBs(songExample);
                    if(songs.size() == 0)
                        return;

                    List<Song> collect = songs.stream().filter(distinctByKey(Song::getSongid)).collect(Collectors.toList());
//                    List<String> lrc = songs.stream().map(Song::getLrc).collect(Collectors.toList());

                    collect.forEach(song->{
                        if(song.getLrc() == null || "[00:00.00] 暂无歌词".equals(song.getLrc())) {
                            return;
                        }
                        Future submit = executorService.submit(new AnalysisRunnable());


                    });
                } else {
                    System.out.println("歌单id" + songListId + "对应的歌曲为空");
                }

            }

        });
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    public static String getClearLytric(String word1) {
        StringBuilder builder2 = new StringBuilder();
        String regex1 = "[\u4E00-\u9FA5|a-zA-Z].*\\n";
        Pattern pattern1 = Pattern.compile(regex1);

        Matcher matcher2 = pattern1.matcher(word1);
        while(matcher2.find()) {
            builder2.append(matcher2.group(0));
        }
        return  builder2.toString();

    }

}
