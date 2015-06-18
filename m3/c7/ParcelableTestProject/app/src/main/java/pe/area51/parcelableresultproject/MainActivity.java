package pe.area51.parcelableresultproject;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_EVENT = "pe.area51.parcelableresultproject.EVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_button_previewevent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PreviewActivity.class).putExtra(EXTRA_EVENT, createEvent()));
            }
        });
    }

    private Event createEvent() {
        final Person author = new Person("Pedro", "Ortega", 23);
        final Person[] guests = {
                new Person("Juan", "García", 24),
                new Person("Eliana", "Roncal", 24)
        };
        final Location location = new Location("gps");
        location.setLatitude(-12.1193102);
        location.setLongitude(-77.0329022);
        return new Event("Clase Android", System.currentTimeMillis(), "Clase 7 del módulo 3", location, author, Arrays.asList(guests));
    }
}
