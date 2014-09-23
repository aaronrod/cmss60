package android.cmss60.mobile_demo;


import android.app.Activity;
import android.cmss60.R;
import android.cmss60.core.SocialTVApplication;
import android.cmss60.core.VideoPlayer;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;


public class DemoActivity extends Activity {

    private static final String TAG = "DemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_demo);

        Resources res = getResources();
        ImageButton remoteVidButton= (ImageButton)findViewById(R.id.remoteVideoButton);
        Picasso.with(this).load(res.getString(R.string.remote_vod_image_url)).into(remoteVidButton);

    }

    private void startPlayer(String url) {
        Intent i = new Intent(this, VideoPlayer.class);
        i.putExtra(VideoPlayer.KEY_URL, url);
        startActivity(i);
    }

    //**************************************************************************************
    //The following public methods are connected to buttons in the xml layout

    public void clickLocalVideo(View v) {
        //This yields "android.resource://demo.example.cmss60/raw/2130968576" where the
        // number on the right is produced by the automatically generated R class
        String url = "android.resource://" + getPackageName() + "/raw/" + R.raw.sunflowers;
        startPlayer(url);
    }

    /**
     * Big Buck bunny: http://en.wikipedia.org/wiki/Big_Buck_Bunny
     * @param v
     */
    public void clickRemoteVideo(View v) {
        Resources r = getResources();
        String url = r.getString(R.string.remote_vod_url);
        startPlayer(url);
    }

    public void clickHlsVideo(View v) {
        String url = "http://playertest.longtailvideo.com/adaptive/captions/playlist.m3u8";
        startPlayer(url);
    }

    public void clickWebBrowser(View v) {
        // launch BrowserActivity
        final Intent browserIntent = new Intent(this, BrowserActivity.class);
        startActivity(browserIntent);
    }

    public void clickJavaScriptInterface(View v) {
        // launch JSInterfaceActivity
        final Intent jsIfaceIntent = new Intent(this, JSInterfaceActivity.class);
        startActivity(jsIfaceIntent);
    }

    public void clickYouTube(View v) {
        Intent intent = new Intent(this, GridViewActivity.class);
        startActivity(intent);
    }

}
