package android.cmss60.mobile_demo;


import android.app.Activity;
import android.cmss60.R;
import android.cmss60.core.SocialTVApplication;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class BrowserActivity extends Activity {

    private static final String TAG = "BrowserActivity";

    private static final int HDLR_MSG_WEBVIEW_INITIALIZED = 100;

    private WebBrowser mWebBrowser;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (((SocialTVApplication)getApplication()).isAppExiting()) {
                // don't process any more messages
                return;
            }
            Log.v(TAG, "messages handler got event " + msg);
            switch (msg.what) {
                case HDLR_MSG_WEBVIEW_INITIALIZED:
                    Log.v(TAG, "Handler got WebView initialized message");
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        showWebBrowser();
    }

    private void showWebBrowser() {

        mWebBrowser = (WebBrowser) findViewById(R.id.activity_browser_webbrowser);

        mWebBrowser.init(mHandler, HDLR_MSG_WEBVIEW_INITIALIZED);
    }
}
