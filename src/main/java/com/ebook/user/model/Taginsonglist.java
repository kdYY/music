package com.ebook.user.model;

public class Taginsonglist {
    private Integer id;

    private String songlistid;

    private Integer tagid;

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

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    @Override
    public String toString() {
        return "Taginsonglist{" +
                "id=" + id +
                ", songlistid='" + songlistid + '\'' +
                ", tagid=" + tagid +
                '}';
    }
}