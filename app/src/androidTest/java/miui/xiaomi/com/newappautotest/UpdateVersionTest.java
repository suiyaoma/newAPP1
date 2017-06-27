package miui.xiaomi.com.newappautotest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * @author wangmeng
 * @date 17/6/15
 */
@RunWith(AndroidJUnit4.class)
public class UpdateVersionTest {
    private UiDevice myDevice;

    @Before
    public void setUp() throws Exception {
        TestUtils.setUp();
    }

    //todo 1. 如何获取到手机端的plaform的版本？
    // 需要将demo 的minPlatform降小，从而可以在高版本的platform上跑

    //rpk1();rpk2();
    //仅更新versioncode
    @Test
    public void upadteVersioncode() throws InterruptedException{

    }

}
