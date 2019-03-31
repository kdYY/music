package com.ebook.user.controller;

import com.ebook.user.service.MusicService;
import com.ebook.util.config.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kd")
public class MusicController {
    @Autowired
    private MusicService musicService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据歌单id获取歌曲分页列表
     */
    @GetMapping("getMusicSheetById")
    public Payload getMusicSheetById(@RequestParam(name = "songlistId") String songlistId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws Exception {
        return new Payload(musicService.getSongListPage(songlistId, pageNum, pageSize));
    }

    /**
     * 获取单个歌曲
     */
    @GetMapping("getSongById")
    public Payload getSongById(@RequestParam(name = "songId") String songId) throws Exception {
        return new Payload(musicService.getSongBySongId(songId));
    }

    /**
     * 根据标签获取歌单分页列表 pageSize个， 第pageNum页
     */
    @GetMapping("getSongSheetByTagId")
    public Payload getSongSheetByTagId(@RequestParam(name = "tagId") Integer tagId,
                                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws Exception {
        return new Payload(musicService.getSongSheetPage(tagId, pageNum, pageSize));
    }


    /**
     * 获取所有标签
     */
    @GetMapping("getAllTag")
    public Payload getAllTag() throws Exception {
        return new Payload(musicService.getAllTag());
    }

    /**
     * 获取tagId下热门歌单
     */
    @GetMapping("getHotSongSheet")
    public Payload getHotSongSheet( @RequestParam(name = "tagId") Integer tagId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws Exception {
        return new Payload(musicService.getTopPlayCountSongSheet(tagId, pageNum, pageSize));
    }

    @GetMapping("searchSong")
    public Payload searchSong( @RequestParam(name = "songName") String songName,
                               @RequestParam(name = "userId", required = false, defaultValue = "-1") Integer userId,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws Exception {
        return new Payload(musicService.searchSong(songName,userId, pageNum, pageSize));
    }





}
