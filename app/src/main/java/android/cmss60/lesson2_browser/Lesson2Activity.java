package android.cmss60.lesson2_browser;

/**
 * Objectives:
 *
 * 1. Look at WebBrowser
 * 2. Handler
 * 3. ProgressBar Spinner
 *
 */

import android.app.Activity;
import android.cmss60.R;
import android.cmss60.core.SocialTVApplication;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;

public class Lesson2Activity extends Activity {

    private static final String TAG = "Lesson2Activity";

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
        setContentView(R.layout.activity_lesson2_browser);

        showWebBrowser();
    }

    private void showWebBrowser() {

        mWebBrowser = (WebBrowser) findViewById(R.id.activity_browser_webbrowser);

        ProgressBar spinner = (ProgressBar)findViewById(R.id.webview_progressBar);
        mWebBrowser.setProgressBarSpinner(spinner);

        mWebBrowser.init(mHandler, HDLR_MSG_WEBVIEW_INITIALIZED);
    }
}
