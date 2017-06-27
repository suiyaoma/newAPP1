//package miui.xiaomi.com.newappautotest;
//
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;
//import android.support.test.uiautomator.By;
//import android.support.test.uiautomator.UiDevice;
//import android.support.test.uiautomator.UiObject2;
//
//import com.xiaomi.xmpush.server.Message;
//import com.xiaomi.xmpush.server.Result;
//import com.xiaomi.xmpush.server.Sender;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author wangmeng
// * @date 17/6/20
// */
//@RunWith(AndroidJUnit4.class)
//public class PushTest {
//        //测试组专用A
//    private static final String APP_SECRET = "dr6Lgod46GNjFDCyNSaowg==";
//    private static final String PACKAGE_NAME = "com.xiaomi.mina.monitor1";
//    public static String regId;
//    private UiDevice myDevice;
//
//    @Before
//    public void setUp() throws Exception {
//        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        TestUtils.setUp();
//    }
//
//    @Test
//    public void pushTest() throws Exception{
//        leftToRightSwipe(myDevice);
//        Thread.sleep(1000);
//        myDevice.findObject(By.res("com.miui.home.launcher.assistant:id/action_search_text")).click();
//        Thread.sleep(2000);
//        myDevice.findObject(By.res("com.android.quicksearchbox:id/search_src_text")).setText("测试");
//        Thread.sleep(2000);
//        myDevice.findObject(By.res("com.android.quicksearchbox:id/action")).click();
//        Thread.sleep(4000);
//        myDevice.findObjects(By.clazz("android.support.v7.app.ActionBar$Tab")).get(1).click();
//        Thread.sleep(4000);
//        upSwipe(myDevice);
//        Thread.sleep(4000);
//        List<UiObject2> pushBtn = myDevice.findObject(By.clazz("android.support.v4.view.ViewPager")).getChildren().get(0).getChildren().get(0).getChildren();
//        pushBtn.get(pushBtn.size()-1).click();
//        Thread.sleep(2000);
//        upSwipe(myDevice);
//        List<UiObject2> entrance = myDevice.findObjects(By.clazz("android.widget.ImageView"));
//        entrance.get(entrance.size()-1).click();
//        Thread.sleep(4000);
//        UiObject2 orderBtn = myDevice.findObject(By.text("订阅 Push"));
//        orderBtn.click();
//        Thread.sleep(4000);
//        UiObject2 regIdBox = myDevice.findObject(By.clazz("android.widget.EditText"));
//        //{"regId":"/qzKlNwAz3AssNJ9wAhgt151MM3WKAxba27uuBr2V9c="}
//        regId = regIdBox.getText();
//        int indexStart = regId.indexOf(":\"");
//        int indexEnd = regId.lastIndexOf("\"");
//        regId = regId.substring(indexStart+2,indexEnd);
//        test(regId);
//
//    }
//
//    public void test(String regId) throws Exception {
//            List<String> list = new ArrayList<>();
//            list.add(regId);
//
//            Sender sender = new Sender(APP_SECRET);
//            Message message = new Message.Builder()
//                    .restrictedPackageName(PACKAGE_NAME)
//                    .title("Test regId message title sdk") // 通知栏消息的 title
//                    .description("Test regId message desc12") // 通知栏消息的 desc
//                    .payload("Test regId message payload sdk") // 透传消息的 data
//                    .notifyId(11) // 通知栏消息的 notifyId，相同时通知栏消息会被替换
//                    .notifyType(1) // 通知栏消息通知类型
//                    .passThrough(0) // 0通知栏， 1 透传
//                    .extra("hybrid_pn", "") // 点击通知栏后打开新应用的页面
//                    .extra("hybrid_params", "") // 页面所需的参数，例：xxx=111&yyy=222
//                    .extra("key","value") // 透传消息的 extra 部分
//                    .build();
//            Result result = sender.sendHybridMessageByRegId(message, list, 0); //正式线上用此接口
////       Result result = sender.sendHybridMessageByRegId(message, list, true, 0);  // 用外发的 platform 测试时用此接口
//            System.out.println(result);
//        }
//
//
//
//    public void downSwipe(UiDevice myDevice){
//        int height = myDevice.getDisplayHeight();
//        int width = myDevice.getDisplayWidth();
//        myDevice.swipe(width/2,200,width/2,height-200,40);
//    }
//
//    public void upSwipe(UiDevice myDevice){
//        int height = myDevice.getDisplayHeight();
//        int width = myDevice.getDisplayWidth();
//        myDevice.swipe(width/2,height-200,width/2,200,40);
//    }
//
//    public void leftToRightSwipe(UiDevice myDevice){
//        int height = myDevice.getDisplayHeight();
//        int width = myDevice.getDisplayWidth();
//        myDevice.swipe(50,height/2,width-50,height/2,20);
//    }
//}
