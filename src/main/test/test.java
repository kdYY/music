import com.ebook.common.Singleton;
import com.ebook.user.model.SongTag;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
//        String urlsource = "<a title=\"生活没那么简单 也没那么糟糕\" href=\"/playlist?id=2616332564\" class=\"msk\"></a>";
//        String song_regex = "\"/playlist\\?id=(\\d+)?\"";
//        System.out.println(urlsource);
//        // 创建Pattern对象
//        Pattern songIdPattern = Pattern.compile(song_regex);
//        if(true) {
//            Matcher matcher = songIdPattern.matcher(urlsource);
//            List<String> list = new ArrayList<>();
//            while(matcher.find()) {
//                System.out.println(matcher.group().toString());
//                //list.add(matcher.group());
//            }
//        }
//        Singleton.getInstance().lexer("aaaaaaaa", null);

        String words = "[00:24.57]Baby girl, I can’t imagine what it’s like for you\n" +
                "[00:27.39]I got you pregnant now inside there is a life in you\n" +
                "[00:29.95]I know you wonderin’\n" +
                "[00:31.07]if this is gon make me think bout wifing you\n" +
                "[00:33.01]Like if you had my first child\n" +
                "[00:34.39]would I spend my whole life with you\n" +
                "[00:35.97]Now I aint tryna pick a fight with you,\n" +
                "[00:37.90]I’m tryna talk\n" +
                "[00:38.92]Now I aint tryna spend the night with you\n" +
                "[00:40.67]I’m kinda lost see\n" +
                "[00:41.92]I’ve been giving it some thought lately and frankly\n" +
                "[00:43.86]I’m feelin’ like we aint ready and it’s… hold up now\n" +
                "[00:47.17]Let me finish\n" +
                "[00:47.67]Think about it baby me\n" +
                "[00:49.29]and you we still kids ourself\n" +
                "[00:50.42]How we gon raise a kid by ourself?\n" +
                "[00:53.36]Handle biz by ourself\n" +
                "[00:54.30]A nigga barely over 20,\n" +
                "[00:56.73]where the hell we gon live?\n" +
                "[00:57.98]Where am I gon get that money\n" +
                "[00:59.49]I refuse to bring my boy or my girl in this world\n" +
                "[01:02.17]When I aint got shit to give ‘em\n" +
                "[01:04.43]And I’m not with them niggas\n" +
                "[01:05.31]who be knocking girls up and skate out\n" +
                "[01:07.45]Girl, you gotta think bout\n" +
                "[01:08.76]how the options weigh out\n" +
                "[01:10.14]What's the way out?\n" +
                "[01:11.28]And I ain't too proud to tell ya that I cry sometimes\n" +
                "[01:15.93]I cry sometimes about it\n" +
                "[01:18.72]And girl I know it hurt but if this world was perfect\n" +
                "[01:21.72]Then we could make it work but I doubt it\n" +
                "[01:24.47]And I aint too proud to tell ya that I cry sometimes\n" +
                "[01:27.78]I cry sometimes about it\n" +
                "[01:30.72]And girl I know it hurt but if this world was perfect\n" +
                "[01:32.49]Then we could make it work but I doubt it\n" +
                "[01:35.72]To come up to me talkin’ bout abortion\n" +
                "[01:37.85]This my body nigga so don’t think you finna force shit\n" +
                "[01:41.10]See I knew that this is how you act, so typical\n" +
                "[01:43.72]Said you love me,\n" +
                "[01:44.91]oh, but now you flipping like reciprocals\n" +
                "[01:46.35]It figures though,\n" +
                "[01:47.35]should’ve known that you was just another nigga\n" +
                "[01:49.80]No different from them other niggas\n" +
                "[01:51.30]Who be claiming\n" +
                "[01:52.11]that they love you just to get up in them draws\n" +
                "[01:54.99]Knowing all the right things to say\n" +
                "[01:56.43]I let you hit it raw motha****er\n" +
                "[01:58.36]Now I’m pregnant you\n" +
                "[01:59.68]don’t wanna get involved mutha****er\n" +
                "[02:01.12]Tryna take away a life,\n" +
                "[02:02.55]is you God motha****er?\n" +
                "[02:04.24]I don’t think so\n" +
                "[02:05.74]This a new life up in my stomach\n" +
                "[02:07.51]Regardless if I’m your wife\n" +
                "[02:08.95]This new life here I’mma love it\n" +
                "[02:10.70]I ain't budging,\n" +
                "[02:11.26]I’ll do this by my mutha****ing self\n" +
                "[02:13.51]See my momma raised me\n" +
                "[02:14.64]without no mutha****ing help from a man\n" +
                "[02:16.95]But I still don’t understand how you could say that\n" +
                "[02:19.45]Did you forget all those conversations\n" +
                "[02:21.07]that we had way back\n" +
                "[02:22.35]Bout your father and you told me\n" +
                "[02:23.72]that you hate that nigga\n" +
                "[02:24.84]Talkin’ bout he a coward and you\n" +
                "[02:26.23]so glad that you aint that nigga\n" +
                "[02:27.80]Cause he left your mamma\n" +
                "[02:28.67]when she had you and he ain't shit\n" +
                "[02:30.68]And here you go doin’ the same shit\n" +
                "[02:33.37]You shit ain't nigga!\n" +
                "[02:33.94]And I aint too proud to tell ya\n" +
                "[02:37.26]that I cry sometimes\n" +
                "[02:38.94]I cry sometimes about it\n" +
                "[02:41.00]And boy that shit hurt\n" +
                "[02:42.88]And aint nobody perfect,\n" +
                "[02:44.38]still we can make it work but you doubt it\n" +
                "[02:47.34]Now, I aint too proud to tell ya that I cry sometimes\n" +
                "[02:50.67]I cry sometimes about it\n" +
                "[02:53.73]And boy that shit hurt\n" +
                "[02:55.48]And aint nobody perfect,\n" +
                "[02:56.42]still we can make it work but you doubt it\n" +
                "[02:58.93]They say everything happens for a reason\n" +
                "[03:00.61]And people change like the seasons\n" +
                "[03:02.62]They grow apart she wanted him\n" +
                "[03:04.30]to show his heart and say he loved her\n" +
                "[03:06.12]He spoke the magic words\n" +
                "[03:07.36]and on the same day he ****ed her\n" +
                "[03:09.18]Now she wide open\n" +
                "[03:10.19]She put a ring up on his finger if she could\n" +
                "[03:12.69]But he loved her cause the pussy good\n" +
                "[03:14.44]But she aint no wife though\n" +
                "[03:15.82]Uh oh, she tellin’ him\n" +
                "[03:17.01]she missed her period like typo’s\n" +
                "[03:18.81]He panicking, froze up like a mannequin\n" +
                "[03:20.71]A life grows inside her\n" +
                "[03:22.21]now he asking “is it even mine”\n" +
                "[03:24.33]What if this bitch aint even pregnant dawg\n" +
                "[03:26.14]Could she be lying?\n" +
                "[03:27.08]And she be crying cause he acting distant\n" +
                "[03:28.96]Like ever since I told you this nigga\n" +
                "[03:31.21]you acting different\n" +
                "[03:32.08]And all his niggas saying man\n" +
                "[03:33.90]these hoes be trapping niggas\n" +
                "[03:34.85]Playing with niggas emotions\n" +
                "[03:35.98]like they some action figures\n" +
                "[03:37.48]Swear they pregnant get for collateral\n" +
                "[03:38.79]\n" +
                "[03:40.14]It’s like extortion,\n" +
                "[03:41.21]man if that bitch really pregnant\n" +
                "[03:42.46]Tell her get an abortion\n" +
                "[03:44.21]Uh, but what about your seed nigga?\n" +
                "[03:47.21]about your seed\n" +
                "[03:51.15]\n" +
                "[03:58.56]And I aint too proud to tell ya\n" +
                "[04:00.12]that I cry sometimes\n" +
                "[04:00.87]I cry sometimes about it\n" +
                "[04:10.50]And I aint too proud to tell ya\n" +
                "[04:12.24]that I cry sometimes\n" +
                "[04:13.33]I cry sometimes about it";
        String word1 = "[00:00.000] 作曲 : Ju Ju Club\n" +
                "[00:01.000] 作词 : 许常德\n" +
                "[00:12.900]看着你搭 TAXI 孤单地离去，\n" +
                "[00:17.900]全世界只剩我在淋雨，\n" +
                "[00:22.900]想着你可能去谁或谁怀里，\n" +
                "[00:27.900]胡闹猜搞的我无法呼吸。\n" +
                "[00:31.900]明明是好天气却感觉下雨的情绪，\n" +
                "[00:37.898]我和你为何都我对不起你，\n" +
                "[00:42.898]转个弯到街上一个人溜冰，\n" +
                "[00:47.898]要自己象只骄傲的鸭子，\n" +
                "[00:50.898]不要爱的鸭子。\n" +
                "[00:53.898]啊 呵 去吧\n" +
                "[00:56.898]没什么了不起，\n" +
                "[00:58.898]什么都依你却看轻我自己，\n" +
                "[01:03.899]虽然我爱你不许你再孩子气\n" +
                "[01:08.899]寂寞的鸭子\n" +
                "[01:10.899]也可以不要你。\n" +
                "[01:24.899]有时爱会让人变得笨笨地，\n" +
                "[01:29.899]习惯性只去你的心里，\n" +
                "[01:34.899]没有你我的心就像遥控器，\n" +
                "[01:38.899]在每个频道里\n" +
                "[01:41.899]疯狂找你 疯狂想你 疯狂看你。\n" +
                "[01:45.899]啊 呵 去吧\n" +
                "[01:48.899]没什么了不起，\n" +
                "[01:50.899]什么都依你却看轻我自己，\n" +
                "[01:55.899]虽然我爱你不许你再孩子气\n" +
                "[02:00.899]寂寞的鸭子\n" +
                "[02:02.899]也可以不要你。\n" +
                "[02:05.899]啊 呵 去吧\n" +
                "[02:08.900]没什么了不起，\n" +
                "[02:10.900]什么都依你却看轻我自己，\n" +
                "[02:15.900]虽然我爱你不许你再孩子气\n" +
                "[02:20.900]寂寞的鸭子\n" +
                "[02:22.900]也可以不要你。\n" +
                "[02:47.900]啊 呵 去吧\n" +
                "[02:50.900]没什么了不起，\n" +
                "[02:52.900]什么都依你却看轻我自己，\n" +
                "[02:57.900]虽然我爱你不许你带孩子气\n" +
                "[03:02.900]寂寞的鸭子\n" +
                "[03:04.900]也可以不要你。"
                ;
        String test = "[00:00.000] 作曲 : 祈合\n" +
                "[00:01.000] 作词 : 祈合\n" +
                "[00:16.117] 说 有什么 不能说 怕什么\n" +
                "[00:21.389]相信我 不会哭 我不会难过\n" +
                "[00:22.999]错 谁的错 谁能说得清楚\n" +
                "[00:28.118]还不如算我的错\n" +
                "[00:31.988]做 有什么 不敢做\n" +
                "[00:34.558]怕什么 相信我 不在乎\n" +
                "[00:38.280]就算你走了\n" +
                "[00:40.118]落 就算我 的心从十六楼\n" +
                "[00:44.580]落下负一层 B座\n" +
                "[00:46.988]我也不会难过 你不要小看我\n" +
                "[00:50.988]有什么熬不过 大不了唱首歌\n" +
                "[00:54.798]虽然是悲伤的歌 声音有点颤抖\n" +
                "[00:58.798]也比你好得多 我还是很快乐\n" +
                "[01:03.228]我再不会难过 你别太小看我\n" +
                "[01:07.229]有什么熬不过 谁说我不能喝\n" +
                "[01:11.229]我喝得比谁都多 走路有点颠簸\n" +
                "[01:15.288]也比你强得多 我还是很快乐\n" +
                "[01:22.499]\n" +
                "[01:25.379]做 有什么 不忍心\n" +
                "[01:27.748]怕什么 相信我 不在乎\n" +
                "[01:30.748]就算你走了\n" +
                "[01:33.188]落 就算我 的心从十六楼\n" +
                "[01:37.679]落下负一层 B座\n" +
                "[01:40.189]我也不会难过 你不要小看我\n" +
                "[01:44.129]有什么熬不过 大不了唱首歌\n" +
                "[01:48.490]虽然是悲伤的歌 声音有点颤抖\n" +
                "[01:51.218]也比你好得多 我还是很快乐\n" +
                "[01:55.528]我才不会难过 你别太小看我\n" +
                "[02:00.428]有什么熬不过 谁说我不能喝\n" +
                "[02:03.829]我喝得比谁都多 走路有点颠簸\n" +
                "[02:09.480]也比你强得多 我还是很快乐\n" +
                "[02:15.570]\n" +
                "[02:29.207]我也不会难过 你不要小看我\n" +
                "[02:33.317]有什么熬不过 大不了唱首歌\n" +
                "[02:37.317]虽然是悲伤的歌 声音有点颤抖\n" +
                "[02:41.567]也比你好得多 我还是很快乐\n" +
                "[02:45.197]我才不会难过 你别太小看我\n" +
                "[02:49.317]有什么熬不过 烧掉你写的信\n" +
                "[02:53.317]忘掉你喜欢的歌 绑住我的眼睛\n" +
                "[02:57.317]眼泪掉不下来 我还是很快乐";
//        try {
//           // JSONObject keyword = Singleton.getNlpInstace().keyword("如果云知道",words,null);
//            byte[] bytes = word1.getBytes();
//            byte[] byte1 = new byte[512];
//            for (int i = 0; i < bytes.length; i++) {
//                if(i % 512 == 0) {
//                    break;
//                }
//                byte1[i] = bytes[i];
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }




    }


    public static String substringByte(String orignal,int start, int count){

        //如果目标字符串为空，则直接返回，不进入截取逻辑；
        if(orignal == null || "".equals(orignal))
            return orignal;

        //截取Byte长度必须>0
        if (count <= 0)
            return orignal;

        //截取的起始字节数必须比
        if (start < 0)
            start=0;

        //目标char Pull buff缓存区间；
        StringBuffer buff = new StringBuffer();

        try {
            //截取字节起始字节位置大于目标String的Byte的length则返回空值
            if (start >= getStringByteLenths(orignal))
                return "";
            // int[] arrlen=getByteLenArrays(orignal);
            int len = 0;
            char c;
            //遍历String的每一个Char字符，计算当前总长度
            //如果到当前Char的的字节长度大于要截取的字符总长度，则跳出循环返回截取的字符串。
            for (int i = 0; i < orignal.toCharArray().length; i++) {
                c = orignal.charAt(i);
                //当起始位置为0时候
                if(start==0){
                    len += String.valueOf(c).getBytes().length;
                    if(len <= count)
                        buff.append(c);
                    else
                        break;
                }else{
                    //截取字符串从非0位置开始
                    len += String.valueOf(c).getBytes().length;
                    if(len >= start && len <= start+count) {
                        buff.append(c);
                    }
                    if(len > start+count)
                        break;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //返回最终截取的字符结果;
        //创建String对象，传入目标char Buff对象
        return new String(buff);
    }

    public static int getStringByteLenths(String args) throws UnsupportedEncodingException{
        return args!=null && args != "" ? args.getBytes().length : 0;
    }

    public void setLycEmotion(String test) {
        StringBuilder builder = new StringBuilder();
        String regex1 = "[\u4E00-\u9FA5|a-zA-Z].*\\n";
        Pattern pattern1 = Pattern.compile(regex1);

        Matcher matcher2 = pattern1.matcher(test);
        while(matcher2.find()) {
            builder.append(matcher2.group(0));
        }
        System.out.println(builder.toString());
        JSONObject keyword = Singleton.getNlp_135().sentimentClassify(builder.toString(),new HashMap<String, Object>());
        try {
            JSONArray items = keyword.getJSONArray("items");
            if(items != null && items.length() != 0) {
                for (int i = 0; i < items.length(); i++) {
                    SongTag songEmotion = new SongTag();
                    songEmotion.setPositiveProb(setDoubleValue(items, i, "positive_prob"));
                    songEmotion.setSentiment(setIntValue(items, i, "sentiment"));
                    songEmotion.setConfidence(setDoubleValue(items, i, "confidence"));
                    songEmotion.setNegativeProb(setDoubleValue(items, i, "negative_prob"));
                    System.out.println(songEmotion.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Double setDoubleValue(JSONArray jsonArray, int i, String key ) {
        try {
            return jsonArray.getJSONObject(i).getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public static int setIntValue(JSONArray jsonArray, int i,  String key ) {
        try {
            return jsonArray.getJSONObject(i).getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
