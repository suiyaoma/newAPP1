package miui.xiaomi.com.newappautotest;

import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cui Liang on 17-5-16.
 */

@RunWith(AndroidJUnit4.class)
public class GlobalSearchTest {
    private UiDevice myDevice;

    public String strCpNames = "快看漫画,网易新闻,今日头条";

    public String strKuanKan = "都市漫画,漫画在线观看,恐怖漫画,动漫,快看,妖怪新娘,快看漫画,耽美漫画,漫画连载,奇幻漫画,灵异漫画,治愈漫画,都市漫画,爆漫画笑,恐怖漫画,恋爱漫画,古风漫画,校园漫画,雪男,反转现实,西街44号,恋爱漫画,古风漫画,捡到只小狐狸,治愈漫画,kuaikanmanhua,国漫,你的血很甜,少女漫画,kkmh,爆笑漫画,灵异漫画,巫祝少女,高清漫画,山神与小枣,自来水之污,漫画大全,条漫,免费漫画,王牌校草,kuaikan,快把哥带走,甜美的咬痕,校园漫画,看漫画,不良诱惑,少年漫画,漫画";

    public String strArticle = "科技资讯,娱乐图片,搞笑图片,呆萌图片,美女图片,汽车资讯,房产资讯,本地新闻,本地资讯,游戏资讯,国际资讯,历史资讯,历史故事,时尚资讯,生活技能,生活小妙招,生活资讯,娱乐资讯,娱乐新闻,体育资讯,体育新闻,教育资讯,搞笑段子,健康生活信息,影视资讯,财经资讯,搞笑内容,影视新闻,影视资讯,热门资讯,热门新闻,呆萌视频,原创视频,小品视频,游戏视频,搞笑视频,游戏视频,音乐视频,社会,今日头条,军事,热点,头条,资讯";
    private String strNoResultOfArticle = "新闻";

    public String strNetease = "轻松一刻,人间栏目,人间频道,独家新闻,独家评论,独家资讯,独家视频,独家直播,163,网易跟帖,网易跟贴,王三三,人文地理,汽车图片,数码资讯,娱乐新闻,娱乐八卦,体育直播,科技资讯,房产信息,手机资讯,公益新闻,财经新闻,楼市新政,沪指大盘,美股行情,明星八卦,人民的名义,国内新闻,国际新闻,港股行情,新闻评论,新闻专题,最新消息,资讯阅读,新闻调查,热点关注,晚报,要闻,股市,新闻联播,深度观察,社会新闻,今日新闻,网易号,网易新闻,wangyi,八卦,专栏,时事,军事,今日热点,国足,热点,网易,政府要闻";
    private String strNoResultOfNetease = "NBA直播,自媒体,";


    private Map<String, String> mapKeyWords = new HashMap<>();

    @Before
    public void testInit() {
        mapKeyWords.put("快看漫画mini", strKuanKan);
        mapKeyWords.put("今日头条", strArticle);
        mapKeyWords.put("网易新闻", strNetease);
    }

    @Test
    public void testCpKeyWordsCheck() throws Exception {
        int index = 0;

        List<String> listErrorWords = new ArrayList<>();

        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();
        myDevice.swipe(300, 0, 300, 800, 50);
        myDevice.waitForIdle(2000);

        UiObject2 searchInput = myDevice.findObject(By.res("com.android.systemui:id/search_hint"));
        searchInput.click();

//        myDevice.waitForIdle();
        Thread.sleep(1000);

        UiObject2 searchText = myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text"));

//        searchText = myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text"));

        Thread.sleep(1000);

//        for (int j = 0; j < 9; j++) {
            for (Object name : mapKeyWords.keySet()) {
                String keys = mapKeyWords.get(name);

                System.out.println("CP:" + name.toString());

                String[] arrayKuaiKanKeys = keys.split(",");

                for (String s : arrayKuaiKanKeys) {
                    searchText = myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text"));
                    myDevice.waitForIdle();
                    searchText.setText("");

                    myDevice.waitForIdle(2000);

                    searchText.setText(s);
                    System.out.println("开始搜索("+ index++ +")：\"" + s + "\"");

//                myDevice.waitForIdle(2000);
                    Thread.sleep(6000);

                    //UiObject2 btnMiaokai = myDevice.findObject(By.text(name.toString()));
                    UiObject2 btnMiaokai = myDevice.findObject(By.descContains("秒开"));


//                    for (int i = 0; i < 2; i++) {
//                        if (btnMiaokai == null) {
//                            System.out.println("Error：无结果或者超时");
////                        myDevice.waitForIdle(1000);
//                            Thread.sleep(2000);
//                            btnMiaokai = myDevice.findObject(By.text(name.toString()));
//                        }
//
//                    }

//                    assertNotNull(myDevice.findObject(By.text(name.toString())));
                    if (btnMiaokai == null) {//定位不到该元素,btnMiaokai始终为null
                        System.out.println("Error：无结果或者超时");
                        listErrorWords.add("第"+ index + "次, CP=" + name.toString() + "|Key=" + s);
                    } else {
                        //System.out.println("返回结果：\"" + s + "\"");
                        System.out.println("返回结果：\"" + name.toString() + "\"");
                    }
                }

            }
//        }

        System.out.println("测试结束，共有"+index+"次请求，"+ listErrorWords.size() + "次超时（6秒）发生");// 测试结束，共有148次请求，146次超时（6秒）发生
        for (String str : listErrorWords) {
            System.out.println(str);
        }

        // assert whether has the error key words

        assertTrue(listErrorWords.size() == 0);


    }

    @Test
    public void testStartCpApp() throws InterruptedException {
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        String[] cpNames = strCpNames.split(",");

        for (String n :cpNames) {

            TestUtils.clearNewAppCache();

            myDevice.pressHome();

            Thread.sleep(6000);

            myDevice.swipe(300, 0, 300, 800, 50);
//            myDevice.waitForIdle(2000);

            Thread.sleep(2000);

            UiObject2 searchInput = myDevice.findObject(By.res("com.android.systemui:id/search_hint"));
            searchInput.click();

            Thread.sleep(2000);

            UiObject2 searchText = myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text"));
            searchText = myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text"));
            myDevice.waitForIdle();
            searchText.setText("");

            myDevice.waitForIdle(2000);

            searchText.setText(n);
            System.out.println("开始搜索：\"" + n + "\"");

            Thread.sleep(3000);

            //UiObject2 btnMiaokai = myDevice.findObject(By.text("秒开"));
            UiObject2 btnMiaokai = myDevice.findObject(By.descContains("秒开"));
            btnMiaokai.click();

            Thread.sleep(6000);

            UiObject2 objectOfApp = null;
            switch (n) {
                case "快看漫画":
                    objectOfApp = myDevice.findObject(By.text("热门推荐"));
                    assertNotNull(objectOfApp);
                    break;
                case "网易新闻":
                    objectOfApp = myDevice.findObject(By.text("网易新闻"));
                    //没有可以唯一定位的元素
                    int i = myDevice.findObject(By.clazz("android.widget.ScrollView")).getChildren().get(0).getChildren().get(2).getChildren().size();
                    assertEquals(i,2);
                    //assertNotNull(objectOfApp);
                    break;
                case "今日头条":
                    objectOfApp = myDevice.findObject(By.text("推荐"));
                    assertNotNull(objectOfApp);
                    break;
            }



        }



    }


    @Test
    public void testRecentAppStartCp() throws InterruptedException {
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        String[] cpNames = strCpNames.split(",");
        for (String n : cpNames) {
            myDevice.pressHome();
            Thread.sleep(2000);
            myDevice.swipe(300, 300, 300, 800, 50);
//            myDevice.waitForIdle(2000);
            Thread.sleep(2000);
            UiObject2 searchInput = myDevice.findObject(By.res("com.android.systemui:id/search_hint"));
            searchInput.click();
            Thread.sleep(2000);

            UiObject2 searchText = myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text"));
            myDevice.waitForIdle();
            searchText.setText("");

            UiObject2 recentAppBar = myDevice.findObject(By.res("com.android.quicksearchbox:id/recent_apps"));//recentAppBar为null
            Thread.sleep(2000);
            //UiObject2 appButton = recentAppBar.findObject(By.textContains(n));//报错
            myDevice.pressBack();
            Thread.sleep(3000);
            UiObject2 appButton = myDevice.findObject(By.textContains(n));
            if (appButton != null) {
                appButton.getParent().click();
                Thread.sleep(6000);

                UiObject2 objectOfApp = null;
                switch (n) {
                    case "快看漫画":
                        objectOfApp = myDevice.findObject(By.text("热门推荐"));
                        assertNotNull(objectOfApp);
                        break;
                    case "网易新闻":
                        objectOfApp = myDevice.findObject(By.clazz("android.widget.ScrollView")).getChildren().get(0).getChildren().get(2).getChildren().get(1);

                        assertNotNull(objectOfApp);
                        break;
                    case "今日头条":
                        objectOfApp = myDevice.findObject(By.text("推荐"));
                        assertNotNull(objectOfApp);
                        break;
                }
            }
        }
    }


    @After
    public void testAfter() {
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        myDevice.pressHome();
        myDevice.pressHome();
    }

}
