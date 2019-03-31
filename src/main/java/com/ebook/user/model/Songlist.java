package com.ebook.user.model;

public class Songlist {
    private String songlistid;

    private String songlistname;

    private String songlistpic;

    private Integer songlistcount;

    private Long songlistplaycount;

    private Integer emotionvalue;

    private Integer hot;

    public String getSonglistid() {
        return songlistid;
    }

    public void setSonglistid(String songlistid) {
        this.songlistid = songlistid == null ? null : songlistid.trim();
    }

    public String getSonglistname() {
        return songlistname;
    }

    public void setSonglistname(String songlistname) {
        this.songlistname = songlistname == null ? null : songlistname.trim();
    }

    public String getSonglistpic() {
        return songlistpic;
    }

    public void setSonglistpic(String songlistpic) {
        this.songlistpic = songlistpic == null ? null : songlistpic.trim();
    }

    public Integer getSonglistcount() {
        return songlistcount;
    }

    public void setSonglistcount(Integer songlistcount) {
        this.songlistcount = songlistcount;
    }

    public Long getSonglistplaycount() {
        return songlistplaycount;
    }

    public void setSonglistplaycount(Long songlistplaycount) {
        this.songlistplaycount = songlistplaycount;
    }

    public Integer getEmotionvalue() {
        return emotionvalue;
    }

    public void setEmotionvalue(Integer emotionvalue) {
        this.emotionvalue = emotionvalue;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}