package android.cmss60.lesson1_youtube;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import static android.content.SharedPreferences.Editor;


public class SetVideoPrefAsyncTask extends AsyncTask<VideoPreference, Void, String> {
    private static final String TAG = "SetPrefAsyncTask";

    public static final String PREF_FILE_NAME = "video_url_prefs";
    public static final String KEY_URL = "video_url";
    private VideoPreference vidPref;
    private SharedPreferences sharedPrefs;

    public SetVideoPrefAsyncTask(SharedPreferences sharedPrefs){
        this.sharedPrefs = sharedPrefs;
    }

    /**
     * This part is done in a different thread from the UI. Most of the work should be done here.
     * @param paramsArray
     * @return
     */
    @Override
    protected String doInBackground(VideoPreference... paramsArray) {
        this.vidPref = paramsArray[0];

        if(vidPref.openEditor()){
            //We want to save the url to the SharedPreferences
            Editor edit = sharedPrefs.edit();
            edit.clear();
            edit.putString(KEY_URL, vidPref.getUrl());
            edit.commit();
        } else {
            //or we want to retrieve a saved url if there is one
            return sharedPrefs.getString(KEY_URL, "");
        }

        return null;
    }

    /**
     * Do minimal work here since it is done on UI thread.
     * @param url
     */
    @Override
    protected void onPostExecute(String url) {
        super.onPostExecute(url);
        vidPref.updateUI(url);
    }

}
