package com.ebook.user.model;

public class SongTag {
    private Integer id;

    private String songid;

    private Double positiveProb;

    private Double negativeProb;

    private Double confidence;

    private Integer sentiment;

    private String tag;

    private Integer hot;

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

    public Double getPositiveProb() {
        return positiveProb;
    }

    public void setPositiveProb(Double positiveProb) {
        this.positiveProb = positiveProb;
    }

    public Double getNegativeProb() {
        return negativeProb;
    }

    public void setNegativeProb(Double negativeProb) {
        this.negativeProb = negativeProb;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Integer getSentiment() {
        return sentiment;
    }

    public void setSentiment(Integer sentiment) {
        this.sentiment = sentiment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}