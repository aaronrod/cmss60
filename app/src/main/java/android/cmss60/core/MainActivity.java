package android.cmss60.core;

import android.app.Activity;
import android.cmss60.lesson0_lifecycle.Lesson0Activity;
import android.cmss60.lesson1_youtube.Lesson1Activity;
import android.cmss60.lesson2_browser.Lesson2Activity;
import android.cmss60.lesson3_videoplayer.Lesson3Activity;
import android.cmss60.lesson4_javascript.Lesson4Activity;
import android.cmss60.mobile_demo.DemoActivity;
import android.content.Intent;
import android.os.Bundle;
import android.cmss60.R;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLesson0(View v){
        Intent i = new Intent(this, Lesson0Activity.class);
        startActivity(i);
    }

    public void clickLesson1(View v){
        Intent i = new Intent(this, Lesson1Activity.class);
        startActivity(i);
    }

    public void clickLesson2(View v){
        Intent i = new Intent(this, Lesson2Activity.class);
        startActivity(i);
    }

    public void clickLesson3(View v){
        Intent i = new Intent(this, Lesson3Activity.class);
        startActivity(i);
    }

    public void clickLesson4(View v){
        Intent i = new Intent(this, Lesson4Activity.class);
        startActivity(i);
    }

    public void clickDemo(View v){
        Intent i = new Intent(this, DemoActivity.class);
        startActivity(i);
    }

}
