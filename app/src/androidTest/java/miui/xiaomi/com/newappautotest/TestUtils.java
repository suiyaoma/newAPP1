package miui.xiaomi.com.newappautotest;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cui Liang on 17-5-24.
 */

public class TestUtils {
    private static UiDevice myDevice;


    public static void clearNewAppCache() throws InterruptedException {
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();
        Thread.sleep(1000);
        myDevice.pressMenu();
        Thread.sleep(4000);
//        UiObject2 newAppInRecentList = myDevice.findObject(By.res("com.miui.home:id/icon_icon"));
        UiObject2 newAppInRecentList = myDevice.findObject(By.text("直达服务"));
        if (newAppInRecentList != null) {
            longClick(myDevice, newAppInRecentList.getParent().getParent());
//  Default long Click not work well, So instead of the method longClick(UiDevice2,UiObject)
//  newAppInRecentList.getParent().getParent().longClick();
            Thread.sleep(6000);
            myDevice.findObject(By.text("清除数据")).click();
            Thread.sleep(3000);
            myDevice.findObject(By.text("确定")).click();
        }

    }

    public static void clearExplorerCache() throws InterruptedException {
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();
        Thread.sleep(1000);
        myDevice.pressMenu();
        Thread.sleep(4000);
        UiObject2 newAppInRecentList = myDevice.findObject(By.text("浏览器"));
        if (newAppInRecentList != null) {
            longClick(myDevice, newAppInRecentList.getParent().getParent());
//  Default long Click not work well, So instead of the method longClick(UiDevice2,UiObject)
//  newAppInRecentList.getParent().getParent().longClick();
            Thread.sleep(6000);
            myDevice.findObject(By.text("清除数据")).click();
            Thread.sleep(3000);
            myDevice.findObject(By.text("确定")).click();
        }
    }

    public static void clearEmailCache() throws InterruptedException{
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();
        Thread.sleep(1000);
        myDevice.pressMenu();
        Thread.sleep(4000);
        UiObject2 newAppInRecentList = myDevice.findObject(By.text("电子邮件"));
        if(newAppInRecentList!=null){
            longClick(myDevice,newAppInRecentList.getParent().getParent());
            Thread.sleep(6000);
            myDevice.findObject(By.text("清除数据")).click();
            Thread.sleep(3000);
            myDevice.findObject(By.text("确定")).click();
        }


    }

    public static void longClick(UiDevice uiDevice, UiObject2 uiObject2) {
        uiDevice.swipe(uiObject2.getVisibleBounds().centerX(), uiObject2.getVisibleBounds().centerY(),
                uiObject2.getVisibleBounds().centerX(), uiObject2.getVisibleBounds().centerY(), 36);
    }

    public static void setUp() throws Exception {

        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();

// 环境准备：首次进入浏览器之前需要将hybrid、浏览器的缓存清掉
        TestUtils.clearNewAppCache();
        TestUtils.clearExplorerCache();
        myDevice.pressHome();
        myDevice.waitForIdle(2000);
    }

    //发送Post请求
    public static JSONObject doPost(String url, Map<String, String> paramMap) {

        NameValuePair[] params = processParams(paramMap);
        try {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < params.length; i++) {
                if (i > 0) {
                    buffer.append("&" + params[i].getName() + "=" + URLEncoder.encode(params[i].getValue(), "UTF-8"));
                } else {
                    buffer.append("?" + params[i].getName() + "=" + URLEncoder.encode(params[i].getValue(), "UTF-8"));
                }
            }
            System.out.println("Post: " + url + buffer.toString());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }

        HttpClient client = new HttpClient();
        client.getParams().setCookiePolicy(org.apache.commons.httpclient.cookie.CookiePolicy.IGNORE_COOKIES);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        client.getParams().setParameter("http.protocol.single-cookie-header", true);
        client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        client.getParams().setSoTimeout(20000);


        PostMethod method = new PostMethod(url);
        method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;");
        method.setRequestHeader("Accept-Charset", "UTF-8");
        method.setRequestHeader("Accept-Language", "zh-cn");
        method.setRequestHeader("Cache-Control", "no-cache");
        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        method.setRequestHeader("Connection", "close");
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
        method.setRequestBody(params);

        try {
            client.executeMethod(method);
            String response = readInputStream(method.getResponseBodyAsStream());
            System.out.println(response);
            return new JSONObject(response);
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        } catch (HttpException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            method.releaseConnection();
        }
    }
    private static String readInputStream(InputStream is) throws IOException {
        byte[] b = new byte[4096];
        StringBuilder builder = new StringBuilder();
        int bytesRead = 0;
        while (true) {
            bytesRead = is.read(b, 0, 4096);
            if (bytesRead == -1) {
                return builder.toString();
            }
            builder.append(new String(b, 0, bytesRead, "UTF-8"));
        }
    }

    private static NameValuePair[] processParams(Map<String, String> params) {
        List<NameValuePair> p = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry :params.entrySet()) {
            p.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        return p.toArray(new NameValuePair[p.size()]);
    }
}
