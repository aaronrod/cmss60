package android.cmss60.lesson1_youtube;

import android.content.Context;

/**
 * 
 */
public interface VideoPreference{
    Context getContext();

    /**
     * Determines if we open Editor or if we retrieve a value
     * @return
     */
    boolean isInput();
    String getKey();

    /**
     * @return URL
     */
    String getUrl();

    /**
     * The name of the SharedPreference file
     * @return
     */
    String getFileName();


    /**
     * Since each of the RadioButtons are uniquely tied to a specific URL, we can use this
     * data to map back to the specific button which was checked. Though his is not the most
     * ideal way to handle this data, this approach is simple and gets the point across.
     */
    void postExecute(String url);
}
