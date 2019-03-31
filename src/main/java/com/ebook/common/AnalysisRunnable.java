package com.ebook.common;

import com.baidu.aip.nlp.AipNlp;
import com.ebook.user.model.Song;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class AnalysisRunnable implements Runnable {

    BlockingQueue taskQueue;

    private AipNlp aipNlpA;

    private Song song;

    public AipNlp getAipNlpA() {
        return aipNlpA;
    }

    public void setAipNlpA(AipNlp aipNlpA) {
        this.aipNlpA = aipNlpA;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    /*
        * {
  "log_id": 4095247775740598103,
  "text": "[by:hondoes]\n[00:00.08]Hypnotized, this love out of me\n[00:03.67]Without your air I can't even breathe\n[00:07.67]Lead my way out into the light\n[00:11.32]Sing your lu-lu-lullaby\n[00:15.08]Cherries in the ashtray\n[00:16.95]Take me through the day\n[00:18.88]I just gotta make you drunk in memory\n[00:22.53]See you at the party\n[00:24.46]As long as you're with me\n[00:26.35]Sleeping in my bathtub\n[00:28.01]But can wish you were late\n[00:29.69]\n[00:30.14]Keep me safe up in the clouds\n[00:33.88]Cause I can't come raining down\n[00:37.52]Make the most and sleep in the night\n[00:41.24]Sing your lu-lu-lullaby\n[00:44.57]\n[01:22.63]Hypnotized, this love out of me\n[01:26.09]Without your air I can't even breathe\n[01:30.04]Lead my way out into the light\n[01:33.74]Sing your lu-lu-lullaby\n[01:37.53]Cherries in the ashtray\n[01:39.41]Take me through the day\n[01:41.39]I just gotta make you drunk in memory\n[01:45.13]See you at the party\n[01:47.00]As long as you're with me\n[01:48.81]Sleeping in my bathtub\n[01:50.49]But can wish you were late\n[01:52.20]\n[01:52.62]Keep me safe up in the clouds\n[01:56.32]Cause I can't come raining down\n[02:00.08]Make the most and sleep in the night\n[02:03.77]Sing your lu-lu-lullaby\n[02:07.16]",
  "items": [
    {
      "positive_prob": 0.93406,
      "sentiment": 2,
      "confidence": 0.853466,
      "negative_prob": 0.0659403
    }
  ]
}
        * */

    @Override
    public void run() {

        JSONObject jsonObject = Singleton.getNlpInstace().topic(song.getName(), song.getLrc(), new HashMap<String,Object>());
        //写回数据库

    }
}
