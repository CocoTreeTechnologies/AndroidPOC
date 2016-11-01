package app.mallusolutions.myxmlparser.com;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {
    // All static variables
    static final String URL = "http://api.androidhive.info/pizza/?format=xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView list ;
    LazyAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String,String>> songs;
        XMLParser parser = new XMLParser();

        try {
            songs = parser.getEventsFromAnXML(this);

            list = (ListView)findViewById(R.id.list);
            System.out.println("##########" + songs.toString());

            adapter = new LazyAdapter(this,songs);
            System.out.println("########:" + list);
            list.setAdapter(adapter);

            // Click event for single list row
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                }
            });


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
