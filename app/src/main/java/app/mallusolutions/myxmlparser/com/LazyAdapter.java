package app.mallusolutions.myxmlparser.com;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Phantom on 31-10-2016.
 */

public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a,ArrayList<HashMap<String,String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // imageLoader = new ImageLoader(activity.getApplicationContext());
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null ) {
            vi = inflater.inflate(R.layout.single_list_item_view,null);
        }

        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView artist = (TextView) vi.findViewById(R.id.artist);
        TextView duration = (TextView) vi.findViewById(R.id.duration);
        ImageView thumbnail = (ImageView) vi.findViewById(R.id.list_image);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // Setting all values in listview
        title.setText(song.get(MainActivity.KEY_TITLE));
        artist.setText(song.get(MainActivity.KEY_ARTIST));
        duration.setText(song.get(MainActivity.KEY_DURATION));
        //imageLoader.DisplayImage(song.get(MainActivity.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}
