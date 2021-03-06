package android.cmss60.lesson1_youtube;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import static android.content.SharedPreferences.Editor;


public class SetVideoPrefAsyncTask extends AsyncTask<VideoPreference, Void, String> {
    private static final String TAG = "SetPrefAsyncTask";

    public static final String PREF_FILE_NAME = "video_url_prefs";
    private static final String KEY_URL = "video_url";
    private VideoPreference mVidPref;
    private SharedPreferences mSharedPrefs;

    public SetVideoPrefAsyncTask(SharedPreferences sharedPrefs){
        this.mSharedPrefs = sharedPrefs;
    }

    /**
     * Work here is done in a different thread from the UI.
     * Most of the heavy lifting should be done here.
     *
     * @param paramsArray
     * @return
     */
    @Override
    protected String doInBackground(VideoPreference... paramsArray) {
        // the execute method called by Lesson1Activity can take multiple arguments:
        // e.g. execute(vidPref0, vidPref1, vidPref2);
        // Since we only need one VideoPreference variable for this example, we supplied one and
        // can access it via the first and only element in paramsArray[0]
        this.mVidPref = paramsArray[0];

        if (mVidPref.openEditor()) {
            //We want to save the url to the SharedPreferences
            Editor edit = mSharedPrefs.edit();
            edit.clear();
            edit.putString(KEY_URL, mVidPref.getUrl());
            edit.commit();
        } else {
            //or we want to retrieve a saved url if there is one
            return mSharedPrefs.getString(KEY_URL, "");
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
        mVidPref.updateUI(url);
    }

}
