package android.cmss60.lesson1_youtube;

/**
 * Provides the methods to complete SharedPreferences
 * read/write actions with a single AsyncTask class.
 */
public interface VideoPreference{

    /**
     * Here for clarity, though it's function could be derived from {@link #getUrl()}
     * @return
     */
    boolean openEditor();

    /**
     * @return String url or null
     */
    String getUrl();

    /**
     * Since each of the RadioButtons are uniquely tied to a specific URL, we can use this url
     * data to map back to the specific button which was checked and set it. There are likely
     * millions of more elegant ways to approach this topic.
     */
    void updateUI(String url);
}
