package android.cmss60.lesson4_javascript;


/**
 * Objectives:
 *
 * 1. Review assets
 * 2. Review JavaScript integration
 */

import android.app.Activity;
import android.cmss60.R;
import android.cmss60.mobile_demo.*;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Lesson4Activity extends Activity {

    private static final String TAG = "Lesson4Activity";
    private static final String JS_TAG = "JavaScript";

    private static final String JS_URL = "file:///android_asset/demo_js.html";

    Context mThis;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, "onCreate())");

        mThis = this;

        setContentView(R.layout.activity_jsinterface);

        mWebView = (WebView) findViewById(R.id.activity_jsinterface_webview);
        /// set up web chrome client for Javascript calls like alert to work
        mWebView.setWebChromeClient(new WebBrowserWebChromeClient());
        mWebView.setWebViewClient(new WebBrowserWebViewClient());

        showWebView();
    }

    protected class WebBrowserWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.v(TAG, String.format("onPageFinished() url: %s", url));
            // inject JavaScript function showAndroidToast() into the web page
            // this function logs to logcat and puts up a Toast via JSInterface
            mWebView.loadUrl("javascript:" +
                    "function showAndroidToast(toast) {" +
                    "    Android.log(\"demo_js.html showAndroidToast()\");" +
                    "    Android.showToast(toast);" +
                    "}");
        }
    }

    protected class WebBrowserWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.v(TAG, String.format("onJsAlert() url: %s message %s", url, message));
            result.confirm();
            Toast.makeText(mThis, message, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void showWebView() {

        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        // set up Java<->JavaScript interface
        mWebView.addJavascriptInterface(new JSInterface(this, JS_TAG), "Android");

        mWebView.loadUrl(JS_URL);
    }
}