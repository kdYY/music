package com.ebook.user.model;

public class Song {
    private Integer id;

    private String songid;

    private String name;

    private String singer;

    private String pic;

    private String url;

    private Integer time;

    private String lrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid == null ? null : songid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer == null ? null : singer.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc == null ? null : lrc.trim();
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songid='" + songid + '\'' +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                '}';
    }
}