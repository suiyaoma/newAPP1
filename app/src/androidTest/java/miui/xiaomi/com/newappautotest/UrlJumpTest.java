package miui.xiaomi.com.newappautotest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by Cui Liang on 17/5/24.
 */

//// TODO: 17/6/7  浏览器和hybrid 需要在history列表里面靠前，否则找不到 
@RunWith(AndroidJUnit4.class)
public class UrlJumpTest extends Assert {
    private UiDevice myDevice;
    private String[] urlLink = {"ayibang.com", "kuaikanmanhua.com"};
    private String[] actualResult = {"", "热门推荐"};


    @Before
    public void setUp() throws Exception {

        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();

// 环境准备：首次进入浏览器之前需要将hybrid、浏览器的缓存清掉
        TestUtils.clearNewAppCache();
        TestUtils.clearExplorerCache();
        myDevice.pressHome();
        myDevice.waitForIdle(2000);
    }

    @Test
    public void miInternet() throws Exception {

//TODO: 2.  需要在调起键盘的时候，为英文，不然会打印出中文

        for (int i = 0; i < urlLink.length; i++) {
            UiObject2 internetBtn = myDevice.findObject(By.text("浏览器"));
            internetBtn.click();
            Thread.sleep(1000);

            // whether first start explorer
            UiObject2 alertTitle = myDevice.findObject(By.res("com.android.browser:id/alertTitle"));
            if (alertTitle != null) {
                UiObject2 okBtn = myDevice.findObject(By.res("android:id/button1"));
                okBtn.click();
                Thread.sleep(1000);
            }

            UiObject2 exploreHome = myDevice.findObject(By.res("com.android.browser:id/action_home"));
            exploreHome.click();
            UiObject2 searchInput1 = myDevice.findObject(By.text("搜索或输入网址"));
            searchInput1.click();
            Thread.sleep(1000);

            UiObject2 searchInput2 = myDevice.findObject(By.res("com.android.browser:id/url"));
            searchInput2.setText(urlLink[i]);
            Thread.sleep(1000);
            UiObject2 clickBtn = myDevice.findObject(By.res("com.android.browser:id/rightBtn"));
            clickBtn.click();
            Thread.sleep(6000);
            if (i == 0) {
                //如果是第一次进入浏览器的话不会跳转到cp,要刷新当前页面
                clickBtn = myDevice.findObject(By.res("com.android.browser:id/rightBtn"));
                if (clickBtn != null) {
                    clickBtn.click();
                }
                Thread.sleep(2000);

                // Alert for allow get user location
                UiObject2 cpBtn = myDevice.findObject(By.text("允许"));
                if (cpBtn != null) {
                    cpBtn.click();
                }

                Thread.sleep(3000);
                // confirm there are 3 tab on current view
                List<UiObject2> tabs = myDevice.findObjects(By.clazz("android.support.v7.app.ActionBar$Tab"));
                assertEquals(3, tabs.size());
                //阿姨帮cp界面找不到唯一可供定位的元素？？？？
                System.out.println("首次URL" + urlLink[i] + "跳转成功");
                System.out.println("=====");
                myDevice.pressHome();

            } else if (i == 1) {
                Thread.sleep(3000);
                UiObject2 expectResult = myDevice.findObject(By.text(actualResult[i]));

                //每加一个cp，都需要对应增加actualResult
                assertNotNull(expectResult);
                System.out.println(urlLink[i] + "url 跳转成功");
                myDevice.pressHome();
            }
        }
    }
}



