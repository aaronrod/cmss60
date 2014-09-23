package android.cmss60.core;

import android.app.Activity;
import android.cmss60.R;
import android.content.pm.ActivityInfo;
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
    public static final String KEY_LANDSCAPE = "key_landscape";
    private String mUrl;
    private boolean mIsLandscape;
    private VideoView mVideoView;
    private ProgressBar mProgressBarSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mUrl = extras.getString(KEY_URL);
            mIsLandscape = extras.getBoolean(KEY_LANDSCAPE);
            if(mIsLandscape){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }

        mProgressBarSpinner = (ProgressBar)findViewById(R.id.vid_progressBar);
        mProgressBarSpinner.setVisibility(View.VISIBLE);

        mVideoView = (VideoView)findViewById(R.id.videoView);
        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);
        controller.setAnchorView(mVideoView);
        mVideoView.requestFocus();
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setVideoURI(Uri.parse(mUrl));
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause()");
        super.onPause();
        mVideoView.setKeepScreenOn(false);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.v(TAG, "onCompletion()");
        mVideoView.setKeepScreenOn(false);
        finish(); //closes the Activity and returns to the previous Activity
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.v(TAG, "onPrepared()");
        mProgressBarSpinner.setVisibility(View.GONE);
        mVideoView.setKeepScreenOn(true);
        mVideoView.start();
    }

}
