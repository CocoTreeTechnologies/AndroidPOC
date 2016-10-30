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
    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_COST = "cost";
    static final String KEY_DESC = "description";
    static final String KEY_URL = "URL";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<songs> songs = new ArrayList<>();

        XMLParser parser = new XMLParser();

        try {
            songs = parser.getEventsFromAnXML(this);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // storing string resources into Array
//        String[] adobe_products = getResources().getStringArray(R.array.adobe_products);
//
//        // Binding resources Array to ListAdapter
//        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, adobe_products));
//
//        ListView lv = getListView();
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> parent,View view, int position, long id){
//                String product = ((TextView) view).getText().toString();
//
//                Intent i = new Intent(getApplicationContext(),SingleListItem.class);
//                i.putExtra("product",product);
//                startActivity(i);
//            }


//        });

    }
}
