package com.ebook.user.model;

public class UserSongSheet {
    private Integer id;

    private Integer userid;

    private String songsheetids;

    private String songid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSongsheetids() {
        return songsheetids;
    }

    public void setSongsheetids(String songsheetids) {
        this.songsheetids = songsheetids == null ? null : songsheetids.trim();
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid == null ? null : songid.trim();
    }
}