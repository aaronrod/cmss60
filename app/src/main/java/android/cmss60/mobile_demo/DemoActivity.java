package android.cmss60.mobile_demo;


import android.app.Activity;
import android.cmss60.R;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class DemoActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_demo);
    }


    private void startPlayer(String url){
        Intent i = new Intent(this, VideoPlayer.class);
        i.putExtra(VideoPlayer.KEY_URL, url);
        startActivity(i);
    }

    //**************************************************************************************
    //The following public methods are connected to buttons in the activity_main xml layout

    public void clickLocalVideo(View v){
        //This yields "android.resource://demo.example.cmss60/raw/2130968576" where the
        // number on the right is produced by the automatically generated R class
        String url = "android.resource://" + getPackageName() + "/raw/" + R.raw.sunflowers;
        startPlayer(url);
    }

    public void clickRemoteVideo(View v){
        Resources r = getResources();
        String url = r.getString(R.string.remote_vod_url);
        startPlayer(url);
    }

    public void clickHlsVideo(View v){
        String url = "http://playertest.longtailvideo.com/adaptive/captions/playlist.m3u8";
        startPlayer(url);
    }

    public void clickWebBrowser(View v){
        // launch BrowserActivity
        final Intent browserIntent = new Intent(this, BrowserActivity.class);
        startActivity(browserIntent);
    }

    public void clickJavaScriptInterface(View v) {
        // launch JSInterfaceActivity
        final Intent jsIfaceIntent = new Intent(this, JSInterfaceActivity.class);
        startActivity(jsIfaceIntent);
    }

    public void clickYouTube(View v){
        String url = "http://youtu.be/AOokGMre4AM";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}
