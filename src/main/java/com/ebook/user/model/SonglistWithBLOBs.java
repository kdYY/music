package com.ebook.user.model;

public class SonglistWithBLOBs extends Songlist {
    private String songlistdescription;

    private String tagbody;

    public String getSonglistdescription() {
        return songlistdescription;
    }

    public void setSonglistdescription(String songlistdescription) {
        this.songlistdescription = songlistdescription == null ? null : songlistdescription.trim();
    }

    public String getTagbody() {
        return tagbody;
    }

    public void setTagbody(String tagbody) {
        this.tagbody = tagbody == null ? null : tagbody.trim();
    }
}