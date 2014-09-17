package android.cmss60.mobile_demo;

import android.app.Activity;
import android.cmss60.core.SocialTVApplication;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBrowser extends WebView {

    protected static final String TAG = "WebBrowser";
    protected static final String GOOGLE_PAGE = "http://www.goggle.com";
    protected static final String HLS_TEST_PAGE = "http://www.jwplayer.com/html5/hls";

    private final Activity mActivity;
    private boolean mIsInitialized = false;
    private Handler mOnInitHdlr;
    int mHandlerWhat;

    protected WebChromeClient mWebChromeClient;

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
        // set callback handler
        mOnInitHdlr = handler;
        mHandlerWhat = handleWhat;
        // config WebView
        configWebView();
        // set up web chrome client for Javascript calls like console.log to work
        mWebChromeClient = new WebBrowserWebChromeClient();
        setWebChromeClient(mWebChromeClient);
        // to listen to onPageFinished
        setWebViewClient(new WebBrowserWebViewClient());
        // load first/home page
        // can't call from Java->JS until a page is loaded and we reach onPageFinished()
        loadUrl(HLS_TEST_PAGE);
    }

    protected class WebBrowserWebChromeClient extends WebChromeClient {
        // override methods if needed
    }

    protected class WebBrowserWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            if (!mIsInitialized) {
                // first web page fully loaded
                mIsInitialized = true;
                mOnInitHdlr.sendEmptyMessage(mHandlerWhat);
            }
        }
    }

    private void configWebView() {

        WebSettings settings = getSettings();

        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        // enable JavaScript
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        // append the app specific info to the WebView user agent string
        final String userAgent = settings.getUserAgentString() + " "
                + ((SocialTVApplication) mActivity.getApplication()).getAppUserAgentString();
        settings.setUserAgentString(userAgent);
    }

}
