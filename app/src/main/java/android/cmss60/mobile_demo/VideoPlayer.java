package android.cmss60.mobile_demo;

import android.app.Activity;
import android.cmss60.R;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;


public class VideoPlayer extends Activity
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private static final String TAG = "VideoPlayer";
    public static final String KEY_URL = "key_url";
    private String url;
    private VideoView videoView;
    private ProgressBar progressBarSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            url = extras.getString(KEY_URL);
        }
        progressBarSpinner = (ProgressBar)findViewById(R.id.vid_progressBar);
        progressBarSpinner.setVisibility(View.VISIBLE);

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
    protected void onPause() {
        Log.v(TAG, "onPause()");
        super.onPause();
        videoView.setKeepScreenOn(false);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.v(TAG, "onCompletion()");
        videoView.setKeepScreenOn(false);
        finish(); //closes the Activity and returns to the previous Activity
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.v(TAG, "onPrepared()");
        progressBarSpinner.setVisibility(View.GONE);
        videoView.setKeepScreenOn(true);
        videoView.start();
    }

}
