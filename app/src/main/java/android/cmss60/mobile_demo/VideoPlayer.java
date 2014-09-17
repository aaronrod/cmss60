package android.cmss60.mobile_demo;

import android.app.Activity;
import android.cmss60.R;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoPlayer extends Activity
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private static final String TAG = "VideoPlayer";
    public static final String KEY_URL = "key_url";
    private String url;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            url = extras.getString(KEY_URL);
        }

        videoView = (VideoView)findViewById(R.id.videoView);
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.setOnCompletionListener(this);
        videoView.setOnPreparedListener(this);
        videoView.setVideoURI(Uri.parse(url));
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.v(TAG, "onCompletion()");
        finish(); //closes the Activity and returns to the previous Activity
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.v(TAG, "onPrepared()");
        videoView.start();
    }

}
