package android.cmss60.core;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

public class SocialTVApplication extends Application {

    private static final String TAG = "SocialTVApplication";
    private static final String APP_NAME = "SocialTV";

    private boolean mIsAppExiting = false;
    private String mAppUserAgentString;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");

        // application wide initializations go here
    }

    public boolean isAppExiting() {
        return mIsAppExiting;
    }

    public void setAppExiting(boolean flag) {
        mIsAppExiting = flag;
    }

    public String getAppVersion() {
        try {
            final PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            final int verC = info.versionCode;
            final String verN = info.versionName;
            return verN + "-" + verC;
        } catch (final PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Couldn't get app version");
            e.printStackTrace();
            return null;
        }
    }

    public String getAppUserAgentString() {
        if (mAppUserAgentString == null) {
            // this is essentially a constant, but construction must be deferred to runtime to pick up
            // info from the package manager
            final String appUADeviceInfo =
                    " (Android " + Build.VERSION.RELEASE + "; " + Build.MANUFACTURER + " "
                            + Build.MODEL + " Build/" + Build.DISPLAY + ")";
            final String version = getAppVersion();
            if (version != null) {
                mAppUserAgentString = APP_NAME + "/" + version + appUADeviceInfo;
            } else {
                // Shouldn't happen
                mAppUserAgentString = APP_NAME + "/??" + appUADeviceInfo;
            }
            Log.v(TAG, String.format("App user agent string", mAppUserAgentString));
        }
        return mAppUserAgentString;
    }
}