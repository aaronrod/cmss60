package android.cmss60.lesson1_youtube;

/**
 * Lesson1Activity.java
 *
 * Objectives:
 * 1. Create a button
 * 2. Intents to YouTube app
 * 3. Manifest File
 * 4. SharedPreferences
 * 5. AsyncTask
 */

import android.app.Activity;
import android.cmss60.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import static android.cmss60.lesson1_youtube.SetVideoPrefAsyncTask.PREF_FILE_NAME;


public class Lesson1Activity extends Activity {
    private static final String TAG = "Lesson1Activity";

    //These constants can be placed in String XML resources (See Lesson3 - VideoPlayer)
    private static final String YOUTUBE_MIT_URL = "http://youtu.be/AOokGMre4AM";
    private static final String YOUTUBE_CALTECH_URL = "http://youtu.be/zFH_haNX38E";
    private static final String DEFAULT_URL = "http://youtu.be/8lXdyD2Yzls";

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
        Log.v(TAG, "onResume()");
        //check to see if buttons have been saved previously
        toggleState(null);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "onPause()");
        //save the current state of the RadioButtons
        toggleState(getUrl());
    }

    /**
     * Uses an AsyncTask class for SharedPreferences and optional UI update
     * @param urlToSave may be null
     */
    private void toggleState(final String urlToSave){
        // If there is no URL to save, then there is no need to open Editor
        final boolean openEditor = urlToSave != null;

        SharedPreferences sharedPrefs = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

        new SetVideoPrefAsyncTask(sharedPrefs).execute(new VideoPreference() {

            @Override
            public boolean openEditor() {
                return openEditor;
            }

            @Override
            public String getUrl() {
                return urlToSave;
            }

            @Override
            public void updateUI(String url) {
                if(null == url) return;

                //toggle RadioButtons.
                if(url.compareTo(YOUTUBE_MIT_URL) == 0) {
                    mitRadioButton.setChecked(true);
                    // Since only one RadioButton can be checked at one time, there is no need
                    // to continue checking for a match
                    return;
                }

                if(url.compareTo(YOUTUBE_CALTECH_URL) == 0){
                    caltechRadioButton.setChecked(true);
                    return; // here in case you add more options later
                }

                //do nothing for default
            }
        });
    }

    public void clickYouTube(View v){
        String url = getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    /**
     * Checks the current state of RadioGroup and maps it to a given URL depending on which button
     * is selected. Returns {@code DEFAULT_URL } if no buttons are selected.
     * @return String url
     */
    private String getUrl() {

        if(mitRadioButton.isChecked()) return YOUTUBE_MIT_URL;

        if(caltechRadioButton.isChecked()) return YOUTUBE_CALTECH_URL;

        return DEFAULT_URL;
    }

    //***********************************************
    //Menu Items below to clear RadioButton selection

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
