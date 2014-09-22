package android.cmss60.lesson2_browser;

/**
 * Objectives:
 *
 * 1. Look at WebBrowser
 * 2. Handler
 * 3. ProgressBar Spinner
 * 4. BroadcastReceiver
 *
 */

import android.app.Activity;
import android.cmss60.R;
import android.cmss60.core.SocialTVApplication;
import android.cmss60.core.WebBrowser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Lesson2Activity extends Activity {

    private static final String TAG = "Lesson2Activity";

    private static final int HDLR_MSG_WEBVIEW_INITIALIZED = 100;
    private static final int HDLR_MSG_WIFI_CONNECTION_LOST = 101;
    private static final int HDLR_MSG_WIFI_CONNECTION_ESTABLISHED = 102;

    private Context mThis;
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
                case HDLR_MSG_WIFI_CONNECTION_LOST:
                    Log.v(TAG, "Handler got wifi connection lost message");
                    // pop up a Toast
                    Toast.makeText(mThis, "Social TV: Wifi connection lost", Toast.LENGTH_LONG).show();
                    break;
                case HDLR_MSG_WIFI_CONNECTION_ESTABLISHED:
                    // reload web page
                    Log.v(TAG, "Handler got wifi connection established message");
                    Toast.makeText(mThis, "Social TV: Wifi connection established", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "onReceive()");
            // do not perform long running operations in this callback
            // cannot launch popup dialogs either
            final String action = intent.getAction();
            if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
                if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, true)) {
                    // wifi connection has been established
                    Message msg = mHandler.obtainMessage(HDLR_MSG_WIFI_CONNECTION_ESTABLISHED);
                    mHandler.sendMessage(msg);
                } else {
                    // wifi connection is lost
                    Message msg = mHandler.obtainMessage(HDLR_MSG_WIFI_CONNECTION_LOST);
                    mHandler.sendMessage(msg);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mThis = this;

        setContentView(R.layout.activity_browser);

        // register a receiver to listen to wifi connection changes
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);

        showWebBrowser();
    }

    private void showWebBrowser() {

        mWebBrowser = (WebBrowser) findViewById(R.id.activity_browser_webbrowser);

        ProgressBar spinner = (ProgressBar)findViewById(R.id.webview_progressBar);
        mWebBrowser.setProgressBarSpinner(spinner);

        mWebBrowser.init(mHandler, HDLR_MSG_WEBVIEW_INITIALIZED);
    }
}
