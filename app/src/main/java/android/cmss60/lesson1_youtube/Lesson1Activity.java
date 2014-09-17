package android.cmss60.lesson1_youtube;

/**
 * MainActivity.java
 *
 * Objectives:
 * 1. Create a button
 * 2. Intents to other apps
 * 3. Manifest File
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.cmss60.R;
import android.widget.RadioButton;
import android.widget.Toast;


public class Lesson1Activity extends Activity {
    private static final String YOUTUBE_MIT_URL = "http://youtu.be/AOokGMre4AM";
    private static final String YOUTUBE_CALTECH_URL = "http://youtu.be/zFH_haNX38E";
    public static final String DEFAULT_URL = "http://youtu.be/8lXdyD2Yzls";
    private static final String TAG = "Lesson1Activity";

    private RadioButton mitRadioButton;
    private RadioButton caltechRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_youtube);

        mitRadioButton = (RadioButton)findViewById(R.id.mit_radioButton0);
        caltechRadioButton = (RadioButton)findViewById(R.id.caltech_radioButton1);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //check to see if buttons have been saved previously
        toggleState(false, null);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //save the current state of the RadioButtons
        toggleState(true, getUrl());
    }

    /**
     * Uses an AsyncTask class for read/write from/to SharedPreferences.
     * @param isInput may be null
     * @param urlToSave
     */
    private void toggleState(final boolean isInput, final String urlToSave){
        new SetPrefAsyncTask().execute(new VideoPreference() {
            @Override
            public Context getContext() {
                return Lesson1Activity.this;
            }

            @Override
            public boolean isInput() {
                return isInput;
            }

            @Override
            public String getKey() {
                return "key_url";
            }

            @Override
            public String getUrl() {
                return urlToSave;
            }

            @Override
            public String getFileName() {
                return "video";
            }

            @Override
            public void postExecute(String url) {
                if(null == url) return;

                //toggle RadioButtons. Checks for null in case the Activity has been removed
                if(url.compareTo(YOUTUBE_MIT_URL) == 0 && mitRadioButton != null) {
                    mitRadioButton.setChecked(true);
                    return;
                }

                if(url.compareTo(YOUTUBE_CALTECH_URL) == 0 && caltechRadioButton != null){
                    caltechRadioButton.setChecked(true);
                    return; // here just in case you add more options later
                }

                //else do nothing for default
            }
        });
    }

    public void clickYouTube(View v){
        String url = getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    /**
     * Three possible states of the RadioGroup
     * @return
     */
    private String getUrl() {

        if(mitRadioButton.isChecked()) return YOUTUBE_MIT_URL;

        if(caltechRadioButton.isChecked()) return YOUTUBE_CALTECH_URL;

        return DEFAULT_URL;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.youtube, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.reset_radio_buttons) {
            mitRadioButton.setChecked(false);
            caltechRadioButton.setChecked(false);
            Toast.makeText(this, "Radio Buttons Reset", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
