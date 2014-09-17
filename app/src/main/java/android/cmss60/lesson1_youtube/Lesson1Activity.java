package android.cmss60.lesson1_youtube;

/**
 * MainActivity.java
 *
 * Objectives:
 * 1. Create a button
 * 2. Intents to other apps
 * 2. Manifest File
 */

import android.app.Activity;
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
    private static final String YOUTUBE_DEFAULT_URL = "http://youtu.be/8lXdyD2Yzls";
    private static final String TAG = "Lesson1Activity";

    RadioButton mitRadioButton;
    RadioButton caltechRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_youtube);

        mitRadioButton = (RadioButton)findViewById(R.id.mit_radioButton0);
        caltechRadioButton = (RadioButton)findViewById(R.id.caltech_radioButton1);
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

        return YOUTUBE_DEFAULT_URL;
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
