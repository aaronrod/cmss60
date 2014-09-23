package android.cmss60.lesson3_videoplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.cmss60.R;
import android.cmss60.core.VideoPlayer;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

/**
 * Objectives:
 *
 * 1. Review local VOD, remote VOD, and HLS
 * 2. Review Bundle extras with Intent
 * 3. Review AlertDialog
 * 4. String Resources
 */
public class Lesson3Activity extends Activity {
    private static final String TAG = "Lesson3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
    }

    private void startPlayer(String url, boolean isLandscape){
        if(url == null){
            //Nothing to play...
            return;
        }
        Intent i = new Intent(this, VideoPlayer.class);
        i.putExtra(VideoPlayer.KEY_URL, url);
        i.putExtra(VideoPlayer.KEY_LANDSCAPE, isLandscape);
        startActivity(i);
    }

    public void clickLocalVod(View v){
        //This yields "android.resource://android.cmss60/raw/2130968576" where the
        //number on the right is produced by the automatically generated R class
        String url = "android.resource://" + getPackageName() + "/raw/" + R.raw.sunflowers;
        startPlayerDialog(url);
    }

    public void clickRemoteVod(View v){
        Resources r = getResources();
        String url = r.getString(R.string.remote_vod_url);
        startPlayerDialog(url);
    }

    public void clickHls(View v){
        Resources r = getResources();
        String url = r.getString(R.string.hls_url);
        startPlayerDialog(url);
    }

    public void startPlayerDialog(final String url) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set the title
        alertDialogBuilder.setTitle("Screen Orientation");

        // Set the message and actions for positive and negative buttons
        alertDialogBuilder
                .setMessage("Please choose one or click outside dialog to cancel.")
                .setCancelable(true)
                .setNegativeButton("Portrait",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean isLandscape = false;
                        startPlayer(url, isLandscape);
                    }
                })
                .setPositiveButton("Landscape",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean isLandscape = true;
                        startPlayer(url, isLandscape);
                    }
                });

        // Create dialog and show it
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
