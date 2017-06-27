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

import static org.junit.Assert.assertNotNull;

/**
 * Created by mi on 2017/6/17.
 */

@RunWith(AndroidJUnit4.class)
public class EmailUrlJump {
    private UiDevice myDevice;


    @Before
    public void setUp() throws InterruptedException{
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myDevice.pressHome();
        //clearAppCache
        TestUtils.clearNewAppCache();
        //clearBrowerCache
        TestUtils.clearExplorerCache();
        myDevice.pressHome();
    }

    @Test
    public void emailUrlJump() throws InterruptedException{
        //open email
        String cpUrl = "www.ayibang.com,www.kuaikanmanhua.com";
        String[] urls = cpUrl.split(",");
        for (int i =0; i < urls.length;i++){
            String cpName = urls[i];
            if(i==0){
                TestUtils.clearEmailCache();
                myDevice.pressHome();
                myDevice.findObject(By.text("系统工具")).click();
            }
            myDevice.findObject(By.text("电子邮件")).click();
            Thread.sleep(2000);
            if(i==0) {
                UiObject2 emailAddress = myDevice.findObject(By.text("邮件地址"));
                //emailAddress = myDevice.findObject(By.text("邮件地址"));
                //emailAddress.getText();
                emailAddress.setText("");
                myDevice.waitForIdle(2000);
                emailAddress.setText("xiaomitest1@163.com");
                emailAddress.click();
                UiObject2 password = myDevice.findObject(By.res("com.android.email:id/account_password"));
                Thread.sleep(2000);
                password.setText("xiaomitest111");
                UiObject2 loginBtn = myDevice.findObject(By.text("登录"));
                loginBtn.click();
                Thread.sleep(10000);
                UiObject2 doneBtn = myDevice.findObject(By.res("com.android.email:id/done_btn"));
                doneBtn.click();
                Thread.sleep(12000);
            }else {
                myDevice.findObject(By.res("com.android.email:id/chat_home_btn")).click();
                Thread.sleep(2000);
                myDevice.findObject(By.res("com.android.email:id/chat_home_btn")).click();
                Thread.sleep(1000);
            }
            //send email
            //for (int i = 0; i < urls.length; i++){
            myDevice.findObject(By.text("写邮件")).click();
            Thread.sleep(1000);
            UiObject2 emailBody = myDevice.findObject(By.res("com.android.email:id/body"));
            UiObject2 toSomebody = myDevice.findObject(By.res("com.android.email:id/to"));
            toSomebody.click();
            Thread.sleep(2000);
            toSomebody.setText("xiaomitest1@163.com");
            UiObject2 theme = myDevice.findObject(By.text("主题"));
            theme.click();
            Thread.sleep(1000);
            theme.setText("urltest");
            emailBody.click();
            emailBody.setText(urls[i]);
            Thread.sleep(2000);
            UiObject2 sendBtn = myDevice.findObject(By.text("发送"));
            Thread.sleep(2000);
            sendBtn.click();
            Thread.sleep(2000);
            //}

            //check sended email
            if(i==0){
                myDevice.findObject(By.res("miui:id/home")).click();
                Thread.sleep(2000);
                UiObject2 sendEmail = myDevice.findObject(By.text("已发送邮件"));
                if(sendEmail!=null){
                    sendEmail.click();
                }
            }
            Thread.sleep(4000);
            myDevice.findObjects(By.clazz("android.view.View")).get(0).click();
            Thread.sleep(3000);
            List<UiObject2> chatContent = myDevice.findObjects(By.res("com.android.email:id/chat_content"));
            int len = chatContent.size();
            //for (int i = len; i> len - urls.length;i--){
            chatContent.get(len-1).click();
            Thread.sleep(4000);
            myDevice.findObject(By.res("com.android.email:id/contain_webview")).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).click();
            Thread.sleep(5000);
            myDevice.findObject(By.text("打开")).click();
            Thread.sleep(2000);
            //Thread.sleep(10000);
            UiObject2 continueBtn = myDevice.findObject(By.text("同意并继续"));
            if(continueBtn!=null){
                continueBtn.click();
            }
            Thread.sleep(4000);
            UiObject2 refreshBtn = myDevice.findObject(By.res("com.android.browser:id/rightBtn"));
                    if(refreshBtn!=null){
                        refreshBtn.click();
                    }
            Thread.sleep(4000);
            UiObject2 allowBtn = myDevice.findObject(By.text("允许"));
            if(allowBtn!=null){
                allowBtn.click();
            }
            Thread.sleep(6000);
            UiObject2 objectOfApp = null;
            switch (cpName) {
                case "www.ayibang.com":
                    objectOfApp = myDevice.findObjects(By.clazz("android.support.v7.app.ActionBar$Tab")).get(0);
                    assertNotNull(objectOfApp);
                    break;
                case "www.kuaikanmanhua.com":
                    objectOfApp = myDevice.findObject(By.text("热门推荐"));
                    assertNotNull(objectOfApp);
                    break;
            }
            System.out.print(cpName+"跳转成功");
            myDevice.pressHome();
            // }
        }
    }



    @Test
    public void ucUrlJump() throws InterruptedException {
        //clear AppStoreCache
        myDevice.pressHome();
        Thread.sleep(1000);

        //下载浏览器
        String[] urlBrowsers = {"https://btdownload.baidu.com/fc_click.php?url=0iBdc34Zyf0000KYVfD00000000000aSaH_D6zd90Xm000060s000f00076a00jijZG7etPsbyYNCG000000Kftw0000000Jnec00000000000000000.uHdCIZwsrBtEui4MuAqLUB4Bmy-bIi4WUvYEuA7YmiqLpgP-uv7VuiqbmvNWuWwWrADsPWRsPh7WQvkGuyF9UvkGIyk9UM7Gg1fYPj0snB49TA_0UWd9uA7sTAuW0A7bgLPEIgFWuHYv0ZIGUhuEgv-b5HRLnHTvPWcsPj030A-buy7xpyfqnHfLPWn4P163Pjf0Ivqzu7qGujYdn16dnjmvrHm0mLFW5HcYrjD300.TMw9u1dhmdqzULNYugc0uhPxTMw9u1dLugK1nfK9TZKGujYs0ZKGujYknHDLrHRL0ZK9mvV8myd-5yPEUi4Gph-8Tv99UB4BThqLTvNzgvu9TLf0mgKsUh7VuHLKdQAJ42O0snUL0A7sT7qGUhuEgv-b5HndrHc0ULPxIZ-suHYk0A-1gvG9pykBThN9p1Ys0A7sT7qYXgK-5Hc0IgP-T-qYXgK-5Hcsn0KdmdqdTvNzgv-b5Hc1nHfdrjR40ZKWpjYYnj0snfKdmgwYTWYs0ANW5N0zrjfL0A-1gv7b5HD0uhYq0AuWgvPVmgwWpjYznWn0uy-b5HDsnj6sr7tknHTsn1-xnHDYP1fvg1DsP1nzP7tknjndPHKxnHDLnjckg1DkP103P7tknjT1nH9xnHDvrjRzg1DkPW6kn7tknHmLnWuxnHDLnHD4g1DkP103PNtknHm4rHNxnHDvrHbkg1DkPW04nsKEIhk-XZKxpyfqnH0srHDVuZGxnH0zrj6VuZGxnH01nHmVuZGxnH01nWcVuZGxnH01n1TVuZGxnH0YP1TVuZGxnH0vPjcVnNtknj6dnaY1g1DsrjRvQywlg1DsrHTdQywlg1DsrH6YQHPxnH04rHmVnNtknH0YnidbX-tknH0dniYzg1DknjR3Qywlg1DknjR4QH7xnHDsP1mVP-tknH04ridbX-tknHDknadbX-tknHDzPBY1g1DknHnkQywlg1DknWDLQHwxnHDznW0VuZGxnHDzPH0VnNtknHcLnBdbX-tknHfsnzdbX-tknHfLPBdbX-tknHRYnzYzg1DkPHf3Qywlg1DkPWT3QHwxnHDvP1bVuZGxnHDLPHTVuZGxnHDLPHbVuZGxnHDLPW6VuZGxnHDLP1fVuZGxnHDLrHRVnNtknH6sPaYkg1DznadbX-tknW0kraYkg1DznjczQywlg1DznjRLQH7xnHcsPH6VuZGxnHcsPWRVuZGxnHcknHfVuZGxnHcknWnVnNtknWD1PiYkg1DznHfLQH7xnHczQHPxnHczP10VuZGxnHczP1DVnNtknWcLnBdbX-tknWnznadbX-tknWndnBdbX-tknWnLPadbX-tknWn4nzYzg1Dzn1bYQHFxnHcYnj6VuZGxnHcdnHnVuZGxnHcdnH6VnNtknWRznaYkg1DzPHcvQywlg1DzPHfYQywlg1DzPWcdQywlg1DzPWcLQH7xnHcvnWbVn-tknWmdPzYkg1DzP1R3Qywlg1DzP1TLQH7xnHcLrHnVuZGxnHcLrHRVuZGxnHcLrHTVuZGxnHc3n1cVuZGxnHc3n1nVnNtknW6dPBYzg1DzrjRLQHDzg1DzrjR4Qywlg1DzrjTkQywlg1DzrHfLQywlg1DzrHT3Qywlg1DzrH6zQH7xnHc4rjmVn-tknWb4naYkg1D1njDvQH7xnHnsn1nVnNtkn10LridbX-tzPjInQHDsnH7xPHnYQHwxPWnzniYd0ZIV5Hnk0APCmgFMuNq8myd-5y7bmgKsuhn0Ugfqn10sn0KopHYk0AuY5HD0XyPC5HD0mLuzTHYYPHf3rHc0pgPxIWYs0Zw9udqGujYs0Zw9u1Y0mv9xmLN1IAqVgvFGujYLPfKsTvDqnfKBIA7M5ywl0AFzmgwGU1Yzn0KWThnqPj0snjn0&sid=90a2156d9170c3ef&bdid=2D79AD29EDE80B5D5E17F88DA385093D&ip=1165626578&tm=1497861667869&rank=1&wpt=2&q=%C1%D4%B1%AA%E4%AF%C0%C0%C6%F7"};
        String[] browserNames = {"猎豹浏览器"};
        String[] cpNames = {"阿姨帮","快看漫画"};
        String[] cpUrls = {"www.ayibang.com","www.kuaikanmanhua.com"};
        for(int i = 0; i < urlBrowsers.length;i++){
            myDevice.pressHome();
            myDevice.swipe(500, 500, 50, 500, 20);
            if(myDevice.findObject(By.text("猎豹浏览器"))==null){
                myDevice.pressMenu();
                Thread.sleep(4000);
                UiObject2 newAppInRecentList = myDevice.findObject(By.text("应用商店"));
                if (newAppInRecentList != null) {
                    TestUtils.longClick(myDevice, newAppInRecentList.getParent().getParent());
                    Thread.sleep(6000);
                    myDevice.findObject(By.text("清除数据")).click();
                    Thread.sleep(3000);
                    myDevice.findObject(By.text("确定")).click();
                    Thread.sleep(6000);
                }
                myDevice.pressHome();
                //open AppStore
                myDevice.findObject(By.text("应用商店")).click();
                Thread.sleep(2000);
                UiObject2 continueBtn = myDevice.findObject(By.text("同意并免费使用"));
                if (continueBtn != null) {
                    continueBtn.click();
                    Thread.sleep(3000);
                    myDevice.pressBack();
                }
                Thread.sleep(2000);
                Thread.sleep(1000);
                //open xiaomibrowser
                myDevice.findObject(By.text("浏览器")).click();
                Thread.sleep(6000);
                UiObject2 continueBtn2 = myDevice.findObject(By.text("同意并继续"));
                if(continueBtn2!=null){
                    continueBtn2.click();
                    Thread.sleep(3000);
                }
                UiObject2 urlInput = myDevice.findObject(By.res("com.android.browser:id/search_hint"));
                urlInput.click();
                Thread.sleep(3000);
                myDevice.findObject(By.res("com.android.browser:id/url")).setText(urlBrowsers[i]);
                myDevice.pressEnter();
                Thread.sleep(4000);
                myDevice.findObject(By.res("android:id/button1")).click();
                //downloading browser
                Thread.sleep(100000);
                Thread.sleep(1000);
                myDevice.pressHome();
            }

            //猎豹浏览器
            for (int j = 0; j < cpNames.length; j++) {
                Thread.sleep(2000);
                myDevice.swipe(500, 500, 50, 500, 20);
                UiObject2 browserUC = myDevice.findObject(By.text(browserNames[i]));
                if (browserUC != null) {
                    browserUC.click();
                    Thread.sleep(8000);
                }
                if (i == 0) {
                    if (j == 0) {
                        UiObject2 skipBtn = myDevice.findObject(By.text("跳过"));
                        if(skipBtn!=null){
                            skipBtn.click();
                            Thread.sleep(3000);
                        }
                        UiObject2 closeBtn = myDevice.findObject(By.res("com.ijinshan.browser_fast:id/a5i"));
                        if (closeBtn != null) {
                            closeBtn.click();
                            Thread.sleep(2000);
                        }
                    } else {
                        myDevice.pressBack();
                    }

                    UiObject2 searchBox = myDevice.findObject(By.res("com.ijinshan.browser_fast:id/yk"));
                    if (searchBox != null) {
                        searchBox.click();//click searchbox
                        Thread.sleep(2000);
                    }
                    myDevice.findObject(By.res("com.ijinshan.browser_fast:id/rb")).setText(cpUrls[j]);
                    myDevice.pressEnter();
                    Thread.sleep(6000);
                    UiObject2 refreshBtn = myDevice.findObject(By.res("com.ijinshan.browser_fast:id/rf"));
                    if (refreshBtn != null) {
                        refreshBtn.click();
                        Thread.sleep(8000);
                    }
                    Thread.sleep(8000);
                    UiObject2 objectOfApp = null;
                    String cpName = cpNames[j];
                    switch (cpName) {
                        case "阿姨帮":
                            objectOfApp = myDevice.findObject(By.text("允许"));
                            assertNotNull(objectOfApp);
                            break;
                        case "快看漫画":
                            objectOfApp = myDevice.findObject(By.text("热门推荐"));
                            assertNotNull(objectOfApp);
                            break;
                    }

                    //myDevice.findObject(By.res("android:id/button1")).click();
                    myDevice.pressHome();
                    Thread.sleep(2000);
                    }
            }


        }





    }

}
