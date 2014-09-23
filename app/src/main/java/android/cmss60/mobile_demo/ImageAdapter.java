package android.cmss60.mobile_demo;

import android.cmss60.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private String[] youtubeIds;

    public ImageAdapter(Context context, String[] youtubeIds) {
        this.context = context;
        this.youtubeIds = youtubeIds;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertView == null) {

            gridView = new View(context);

            // get layout from grid_composers.xml
            gridView = inflater.inflate(R.layout.grid_composers, null);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

            Picasso.with(context).load(getUrl(youtubeIds[position])).into(imageView);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    /**
     * Converts YouTube id to the YouTube api for thumbnail 0.jpg. Also available are
     * 1.jpg, 2.jpg, 3.jpg
     *
     *
     * @param youtubeId
     * @return
     *
     */
    private String getUrl(String youtubeId){
        return "http://img.youtube.com/vi/" + youtubeId + "/0.jpg";
    }

    @Override
    public int getCount() {
        return youtubeIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
