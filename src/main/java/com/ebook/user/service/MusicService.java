package com.ebook.user.service;

import com.ebook.user.dao.*;
import com.ebook.user.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MusicService {
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
    private UserSongSheetMapper userSongSheetMapper;
    @Autowired
    private SongTagMapper songTagMapper;

    /**
     * 根据标签获取歌单分页列表 pageSize个， 第pageNum页
     */
    public PageInfo<SonglistWithBLOBs> getSongSheetPage(int tagId, int pageNum, int pageSize) {
        List<Taginsonglist> songlistCollect = taginsonglistMapper.selectPageByTagId(tagId);
        List<String> collect = songlistCollect.stream().map(Taginsonglist::getSonglistid).collect(Collectors.toList());
        if(collect.size() != 0) {
            PageHelper.startPage(pageNum, pageSize);
            SonglistExample example = new SonglistExample();
            SonglistExample.Criteria criteria = example.createCriteria();
            criteria.andSonglistidIn(collect);
            List<SonglistWithBLOBs> songlistWithBLOBs = songlistMapper.selectByExampleWithBLOBs(example);
            if(songlistWithBLOBs.size() != 0) {
                PageInfo<SonglistWithBLOBs> pageInfo = new PageInfo<>(songlistWithBLOBs);
                return pageInfo;
            }
        }
        return  null;
    }

    /**
     * 根据歌单id获取歌曲分页列表
     */
    public PageInfo<Song> getSongListPage(String songListId, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        SongtolistExample example = new SongtolistExample();
        example.createCriteria().andSonglistidEqualTo(songListId);
        example.setOrderByClause("id");
        List<Songtolist> songtolists = songtolistMapper.selectByExample(example);
        PageInfo<Songtolist> pageInfo = new PageInfo(songtolists);

        List<String> collect = pageInfo.getList().stream().map(Songtolist::getSongid).collect(Collectors.toList());
        PageHelper.startPage(pageNum, pageSize);
        SongExample songExample = new SongExample();
        songExample.createCriteria().andSongidIn(collect);
        List<Song> songs = songMapper.selectByExample(songExample);
        PageInfo<Song> songPageInfo = new PageInfo<>(songs);
        return songPageInfo;
    }

    /**
     * 获取单个歌曲
     */
    public Song getSongBySongId(String songId) {
        SongExample songExample = new SongExample();
        songExample.createCriteria().andSongidEqualTo(songId);
        List<Song> songs = songMapper.selectByExampleWithBLOBs(songExample);
        if(songs.size() != 0) {
            return songs.get(0);
        }
        return null;
    }


    /**
     * 获取用户播放历史歌曲
     */
    public PageInfo<Song> getUserHitorySongSheet(int userId) {

        UserSongSheetExample example = new UserSongSheetExample();
        example.createCriteria().andUseridEqualTo(userId);

        return null;
    }

    /**
     * 获取所有标签
     */
    public List<Tag> getAllTag() {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria();
        return  tagMapper.selectByExample(tagExample);
    }


    /**
     * 获取标签下PlayCount最多的歌单
     */
    public PageInfo<Song> getTopPlayCountSongSheet(Integer tagId, Integer pageNum, Integer pageSize) {
        List<Taginsonglist> taginsonglists = taginsonglistMapper.selectPageByTagId(tagId);
        if(taginsonglists.size() == 0) {
            return  null;
        }
        List<String> collect = taginsonglists.stream().map(Taginsonglist::getSonglistid).collect(Collectors.toList());
        PageHelper.startPage(pageNum, pageSize);
        SonglistExample example = new SonglistExample();
        example.createCriteria().andSonglistidIn(collect);
        example.setOrderByClause("songListPlayCount desc");
        List<SonglistWithBLOBs> songlistWithBLOBs = songlistMapper.selectByExampleWithBLOBs(example);
        return new PageInfo(songlistWithBLOBs);
    }

    /**
     * 根据搜索查出根据热度desc排行的歌曲列表
     */
    public PageInfo<Song> searchSong(String songName, Integer userId, Integer pageNum, Integer pageSize) {


        List<Song> songs = songMapper.searchSong("%" + songName + "%");
        if(userId == -1) {
            return new PageInfo(songs);
        }
        //获取用户的标签，进行标签匹配
        String userTagName  = "90后:3|游戏:4|放松:5|轻音乐:1";
        if(userId == -1 || userTagName == null) {
            return new PageInfo(songs);
        }
        //分解用户标签{key=90后, value=3}的map集合
        String[] userTag = userTagName.split("\\|");
        Map<String, Integer> userTapMap = new HashMap<>();
        for (String tag : userTag) {
            String[] split = tag.split(":");
            if(split.length == 2) {
                userTapMap.put(split[0], Integer.valueOf(split[1]));
            }
        }
//        Map<String, Integer> sortedMap  = userTapMap.entrySet().stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (x1, x2) -> x2, LinkedHashMap::new));

        //获取歌曲的标签体
        List<String> songIds = songs.stream().map(Song::getSongid).collect(Collectors.toList());
        SongTagExample songTagExample = new SongTagExample();
        songTagExample.createCriteria().andSongidIn(songIds);
        songTagExample.setOrderByClause("hot desc");
        List<SongTag> songTags = songTagMapper.selectByExample(songTagExample);

        //根据用户画像标签获取 歌曲id:期望值 的map集合
        HashMap<String, Integer> recommendSongIdMap = new HashMap<>();
        songTags.forEach(item-> {
            String itemTag = item.getTag();
            if (itemTag != null) {
                Iterator iter = userTapMap.entrySet().iterator();
                int recommendValue = 0;
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String tag = (String) entry.getKey();
                    Integer tagVal = (Integer) entry.getValue();
                    if (itemTag.contains(tag) && tagVal != null) {
                        recommendValue += tagVal;
                        if(item.getHot() != null) {
                            recommendValue += 0.5 * item.getHot();
                        }
                    }

                }
                recommendSongIdMap.put(item.getSongid(), recommendValue);
            }
        });
        if(songTags.size() == 0) {
            return  new PageInfo(songs);
        }
        List<Song> remmendSong = songs.stream().filter(song -> {
            Integer songRecommendValue = recommendSongIdMap.get(song.getSongid());
            if(songRecommendValue == null) {
                return false;
            }
            song.setRecommendValue(songRecommendValue);
            return true;
        }).sorted(Comparator.comparing(Song::getRecommendValue)).collect(Collectors.toList());

        return new PageInfo(remmendSong);

    }


    /**
     * 获取标签下听取playCount次数最多,hot次多的歌单
     */
    public PageInfo<Song> getTopPlayCountSongSheetByTagName(String tagName, Integer pageNum, Integer pageSize) {

        List<String> tags = new ArrayList<>();
        String[] split = tagName.split("|");
        for (String tag : split) {
            tags.add(tag);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SonglistWithBLOBs> songlistWithBLOBs = songlistMapper.selectByTagName(tags);

        songlistWithBLOBs.forEach(songSheet->{

        });
        return new PageInfo(songlistWithBLOBs);
    }

    /**
     * 获取热门标签中的前n热门歌单
     */


    /**
     * 每次听取歌单,playCount数目+1
     */

    /**
     * 每次听取歌曲,hot数目+1
     */

    /**
     * 定时任务 筛选歌曲打标签
     *  根据tagId 获取songListId, 轮询 songlistId，将每首歌加上标签
     */
    public void innerJob() {
        TagExample tagExample = new TagExample();
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        tags.forEach(tag -> {
            TaginsonglistExample example = new TaginsonglistExample();
            example.createCriteria().andTagidEqualTo(tag.getId());
            List<Taginsonglist> taginsonglists = taginsonglistMapper.selectByExample(example);
            List<String> SonglistIds = taginsonglists.stream().map(Taginsonglist::getSonglistid).collect(Collectors.toList());

            String tagName = tag.getName();

            for (String songListId : SonglistIds) {
                SongtolistExample example1 = new SongtolistExample();
                example1.createCriteria().andSonglistidEqualTo(songListId);
                List<Songtolist> songtolists = songtolistMapper.selectByExample(example1);

                List<String> songIds = songtolists.stream().map(Songtolist::getSongid).collect(Collectors.toList());

                List<SongTag> updateList = new ArrayList<>();
                List<SongTag> insertList = new ArrayList<>();
                if(songIds.size() != 0) {
                    List<SongTag> songTagsExist = songTagMapper.selectBySongIds(songIds);
                    if(songTagsExist.size() != 0) {
                        List<String>  existSongIds = songTagsExist.stream().map(SongTag::getSongid).collect(Collectors.toList());
                        songIds.removeIf(s -> existSongIds.contains(s));
                    }
                    //insert
                    songIds.forEach(songId -> {
                        SongTag songTag = new SongTag();
                        songTag.setSongid(songId);
                        songTag.setTag(tagName);
                        songTag.setHot(tag.getHot());
                        insertList.add(songTag);
                        //songTagMapper.insert(songTag);
                    });
                    //update
                    songTagsExist.forEach(songTag -> {
                        if(!songTag.getTag().contains(tagName)) {
                            songTag.setTag(songTag.getTag() + "|" + tagName);
                        } else {
                            songTag.setTag(null);
                        }
                        songTag.setHot(songTag.getHot() + tag.getHot());
                        updateList.add(songTag);
                    });
                }


                if(updateList.size() != 0) {
                    songTagMapper.updateBatch(updateList);
                }
                if(insertList.size() != 0) {
                    songTagMapper.insertBatch(insertList);
                }
            }
        });

    }

    //定时任务2 对新增歌单重新打标签
    public void innerJobBefore() {
        TagExample tagExample = new TagExample();
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        tags.forEach(tag -> {
            TaginsonglistExample example = new TaginsonglistExample();
            example.createCriteria().andTagidEqualTo(tag.getId());
            List<Taginsonglist> taginsonglists = taginsonglistMapper.selectByExample(example);
            List<String> SonglistIds = new ArrayList<>();
            if(taginsonglists.size() != 0) {
                SonglistIds = taginsonglists.stream().map(Taginsonglist::getSonglistid).collect(Collectors.toList());
            }
            if(SonglistIds.size() != 0) {
                SonglistExample songlistExample = new SonglistExample();
                songlistExample.createCriteria().andSonglistidIn(SonglistIds);
                List<SonglistWithBLOBs> songlistWithBLOBs = songlistMapper.selectByExampleWithBLOBs(songlistExample);

                //全部update
                String tagName = tag.getName();
                //songlistWithBLOBs.removeIf(songSheet->songSheet.getTagbody() != null && songSheet.getTagbody().contains(tagName));
                songlistWithBLOBs.forEach(songSheet->{
                    String tagbody = songSheet.getTagbody();
                    if(tagbody != null) {
                        if(tagbody.contains(tagName)) {
                            songSheet.setTagbody(null);
                        } else {
                            songSheet.setTagbody(songSheet.getTagbody() + "|" + tagName);
                        }
                    } else {
                        songSheet.setTagbody(tagName);
                    }

                    Integer hot = songSheet.getHot();
                    int expect = tag.getHot();
                    if(hot == null) {
                        songSheet.setHot(expect);
                    } else {
                        // TODO
                        songSheet.setHot(expect + hot);
                    }
                });

                songlistMapper.updateBatch(songlistWithBLOBs);
            }


        });
    }





    //日推
    //根据用户画像歌词标签体获取热度最高的前10首歌曲
    public List<Song> recommendSong() {

        return null;
    }

    //根据标签体(用户画像的歌单|..)获取热度最高的10个歌单
    public List<Song> recommendSongSheet() {

        return null;
    }

    //分钟推 放redis
    //根据用户点击歌曲次数行为记录用户一天歌曲路径及其歌曲点击热度 做cos相似度计算
    public void logFromUserAction() {

    }

    /**
     *
     情感分析：对某个歌单的所有歌曲歌词进行情感倾向分析
     //根据用户听歌的emotion的积极消极总值进行平均值计算，推荐标签听的比较多的情感倾向相似的歌曲
     //将songId进行去重
     */
}
