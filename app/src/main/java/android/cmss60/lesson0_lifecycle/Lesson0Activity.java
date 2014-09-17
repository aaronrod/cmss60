package android.cmss60.lesson0_lifecycle;

/**
 * MainActivity.java
 *
 * Objectives:
 * 1. Activity Lifecycle
 * 2. Layouts/Widgets
 * 3. Intents
 * 4. Create Activity and connect it
 */

import android.app.Activity;
import android.cmss60.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class Lesson0Activity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_lesson_zero);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    public void clickToActivityA(View v){
        //TODO complete
    }

    public void clickToActivityb(View v){
        //TODO complete
    }
}
