package android.cmss60.core;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JSInterface {

    private static final String TAG = "JSInterface";

    Context mContext;
    String mLogTag;

    public JSInterface(Context context, String logTag) {
        mContext = context;
        mLogTag = logTag;
    }

    @JavascriptInterface
    public void log(String text) {
        Log.v(mLogTag, text);
    }

    @JavascriptInterface
    public void showToast(String toast) {
        if (toast == null) {
            Log.v(TAG, "toast is null");
        } else {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
