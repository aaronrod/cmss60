package android.cmss60.lesson1_youtube;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;
import static android.content.SharedPreferences.Editor;

/**
 * Created by arodriguez on 9/16/14.
 */
public class SetPrefAsyncTask extends AsyncTask<VideoPreference, Void, String> {
    private static final String TAG = "SetPrefAsyncTask";
    private VideoPreference vidPref;

    @Override
    protected String doInBackground(VideoPreference... paramsArray) {
        this.vidPref = paramsArray[0];
        Context context = vidPref.getContext();
        String prefFileName = vidPref.getFileName();
        String key = vidPref.getKey();
        String url = vidPref.getUrl();

        SharedPreferences sharedPrefs = context.getSharedPreferences(prefFileName, MODE_PRIVATE);
        if(vidPref.isInput()){
            //We want to record the url to the SharedPreferences
            Editor edit = sharedPrefs.edit();
            edit.clear();
            edit.putString(key, url);
            edit.commit();
            Log.v(TAG, "saving url=" + url);
        } else {
            //We want to retrieve a URL
            String savedUrl = sharedPrefs.getString(key, "");
            Log.v(TAG, "saved url=" + savedUrl);
            return savedUrl;
        }

        return null;
    }

    @Override
    protected void onPostExecute(String url) {
        super.onPostExecute(url);
        //retrieving the url
        vidPref.postExecute(url);
    }


}
