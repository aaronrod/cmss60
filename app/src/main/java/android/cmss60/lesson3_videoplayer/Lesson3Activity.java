package android.cmss60.lesson3_videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.cmss60.R;
import android.view.View;

public class Lesson3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
    }

    private void startPlayer(String url){
        Intent i = new Intent(this, Lesson3_VideoPlayer.class);
        i.putExtra(Lesson3_VideoPlayer.KEY_URL, url);
        startActivity(i);
    }

    public void clickLocalVod(View v){
        //This yields "android.resource://demo.example.cmss60/raw/2130968576" where the
        //number on the right is produced by the automatically generated R class
        String url = "android.resource://" + getPackageName() + "/raw/" + R.raw.sunflowers;
        startPlayer(url);
    }

    public void clickRemoteVod(View v){
        Resources r = getResources();
        String url = r.getString(R.string.remote_vod_url);
        startPlayer(url);
    }

    public void clickHls(View v){
        String url = "http://playertest.longtailvideo.com/adaptive/captions/playlist.m3u8";
        startPlayer(url);
    }

}
