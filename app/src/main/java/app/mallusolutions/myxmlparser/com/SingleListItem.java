package app.mallusolutions.myxmlparser.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Phantom on 26-10-2016.
 */

public class SingleListItem extends Activity {
    public ImageLoader imageLoader;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);

        TextView title = (TextView) findViewById(R.id.title);
        TextView artist = (TextView) findViewById(R.id.artist);
        TextView duration = (TextView) findViewById(R.id.duration);
        ImageView thumbnail = (ImageView) findViewById(R.id.list_image);

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        artist.setText(i.getStringExtra("artist"));
        duration.setText(i.getStringExtra("duration"));
        title.setText(i.getStringExtra("title"));

        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

        thumbnail.setImageBitmap(bmp );

    }
}
