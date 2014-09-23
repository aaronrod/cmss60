package android.cmss60.mobile_demo;

import android.app.Activity;
import android.cmss60.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridViewActivity extends Activity implements OnItemClickListener{


    public static final String[] COMPOSER_YOUTUBE_IDS = new String[] {
            "JTc1mDieQI8", // Mozart
            "PCicM6i59_I", // Bach
            "OnWDTqJCXhw", // Tchaikovsky Piano Concerto (BSO visit at Carnegie Hall NYC)
            "3G4NKzmfC-Q", // Smetana ~ Moldau
            "ITTbY1n3Iz8", // Sibelius Violin Concerto (Joshua Bell)
            "6z4KK7RWjmk", // Beethoven Symphony No. 5
            "I002Aq1o3Pg", // Dvorak Cello Concerto (Rostropovich)
            "m0olrJAmX60", // Chopin Polonaise op 53
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_grid);

        GridView gridView = (GridView)findViewById(R.id.demo_gridView);
        gridView.setAdapter(new ImageAdapter(this, COMPOSER_YOUTUBE_IDS));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = getUrl(COMPOSER_YOUTUBE_IDS[i]);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private String getUrl(String youtubeId){
        return "http://youtu.be/" + youtubeId;
    }
}
