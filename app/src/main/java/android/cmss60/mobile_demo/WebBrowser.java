package android.cmss60.mobile_demo;

import android.app.Activity;
import android.cmss60.core.SocialTVApplication;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebBrowser extends WebView {

    protected static final String TAG = "WebBrowser";
    protected static final String CNN_PAGE = "http://www.cnn.com";
    protected static final String CNN_WORLD_PAGE = "http://www.cnn.com/WORLD/";
    protected static final String HLS_TEST_PAGE = "http://www.jwplayer.com/html5/hls";

    private final Activity mActivity;
    private boolean mIsInitialized = false;
    private Handler mOnInitHdlr;
    int mHandlerWhat;
    private ProgressBar mProgressBarSpinner;

    public WebBrowser(Context context) {
        super(context);
        Log.v(TAG, "WebBrowser()");
        mActivity = (Activity) context;
    }

    public WebBrowser(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.v(TAG, "WebBrowser()");
        mActivity = (Activity) context;
    }

    public WebBrowser(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.v(TAG, "WebBrowser()");
        mActivity = (Activity) context;
    }

    public void init(Handler handler, int handleWhat) {
        Log.v(TAG, "init()");

        if(mProgressBarSpinner != null){
            mProgressBarSpinner.setVisibility(View.VISIBLE);
        }

        // set callback handler
        mOnInitHdlr = handler;
        mHandlerWhat = handleWhat;
        // config WebView
        configWebView();
        // to listen to onPageFinished
        setWebViewClient(new WebBrowserWebViewClient());
        // load first/home page
        // can't call from Java->JS until a page is loaded and we reach onPageFinished()
        loadUrl(HLS_TEST_PAGE);
        //loadUrl(CNN_PAGE);
    }

    public void setProgressBarSpinner(ProgressBar progressBar){
        this.mProgressBarSpinner = progressBar;
    }

    protected class WebBrowserWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.v(TAG, String.format("onPageFinished() url: %s", url));
            if (!mIsInitialized) {
                // first web page fully loaded
                mIsInitialized = true;
                mOnInitHdlr.sendEmptyMessage(mHandlerWhat);
            }
            // dismiss progress spinner
            if (mProgressBarSpinner != null) {
                mProgressBarSpinner.setVisibility(View.GONE);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.v(TAG, String.format("shouldOverrideUrlLoading() url: %s", url));
            // If URL is CNN_WORLD_PAGE, load the web page in the Chrome browser
            if (url.contains(CNN_WORLD_PAGE)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mActivity.startActivity(intent);
                return true;
            }
            // load the web page in this WebView
            return false;
        }
    }

    private void configWebView() {
        // configure the WebView
        WebSettings settings = getSettings();
        // enable the builtin zoom controls
        settings.setBuiltInZoomControls(true);
        // allow web pages' viewport meta tag to control the width of viewport
        // or use wide viewport of meta tag is not present
        settings.setUseWideViewPort(true);
        // load the page zoomed out
        settings.setLoadWithOverviewMode(true);
        // enable JavaScript
        settings.setJavaScriptEnabled(true);
        // enable DOM storage in client device
        settings.setDomStorageEnabled(true);
        // append the app specific info to the WebView user agent string
        final String userAgent = settings.getUserAgentString() + " "
                + ((SocialTVApplication) mActivity.getApplication()).getAppUserAgentString();
        settings.setUserAgentString(userAgent);
    }

}
