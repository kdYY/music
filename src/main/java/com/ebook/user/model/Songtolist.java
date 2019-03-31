package com.ebook.user.model;

public class Songtolist {
    private Integer id;

    private String songlistid;

    private String songid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSonglistid() {
        return songlistid;
    }

    public void setSonglistid(String songlistid) {
        this.songlistid = songlistid == null ? null : songlistid.trim();
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid == null ? null : songid.trim();
    }

    @Override
    public String toString() {
        return "Songtolist{" +
                "id=" + id +
                ", songlistid='" + songlistid + '\'' +
                ", songid='" + songid + '\'' +
                '}';
    }
}