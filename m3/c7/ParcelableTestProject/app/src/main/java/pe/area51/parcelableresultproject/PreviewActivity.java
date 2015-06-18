package pe.area51.parcelableresultproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class PreviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_layout);
        final Event event = getIntent().getExtras().getParcelable(MainActivity.EXTRA_EVENT);
        ((TextView) findViewById(R.id.preview_layout_textview_eventname)).setText(event.getName());
        ((TextView) findViewById(R.id.preview_layout_textview_eventdate)).setText(String.valueOf(event.getDate()));
        ((TextView) findViewById(R.id.preview_layout_textview_eventdescription)).setText(event.getDescription());
        ((TextView) findViewById(R.id.preview_layout_textview_eventlocation)).setText(event.getLocation().getLatitude() + ", " + event.getLocation().getLongitude());
        ((TextView) findViewById(R.id.preview_layout_textview_eventauthor)).setText(event.getAuthor().getName() + " " + event.getAuthor().getLastName());
        for (Person guest : event.getGuests()) {
            ((TextView) findViewById(R.id.preview_layout_textview_eventguests)).append(guest.getName() + " " + guest.getLastName() + "\n");
        }
    }
}
