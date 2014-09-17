package android.cmss60.lesson4_javascript;


/**
 * 1. Review assets
 * 2. Review JavaScript integration
 */

import android.app.Activity;
import android.cmss60.R;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Lesson4Activity extends Activity {

    private static final String TAG = "Lesson4Activity";
    private static final String JS_TAG = "JavaScript";

    private static final String JS_URL = "file:///android_asset/demo_js.html";//http://10.1.2.19/demo_js.html";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsinterface);

        showWebView();
    }

    private void showWebView() {

        mWebView = (WebView) findViewById(R.id.activity_jsinterface_webview);

        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        // set up Java<->JavaScript interface
        mWebView.addJavascriptInterface(new JSInterface(this, JS_TAG), "Android");

        mWebView.loadUrl(JS_URL);
    }
}