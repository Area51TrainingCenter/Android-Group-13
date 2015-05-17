package pe.area51.locationapp;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements LocationListener {

    private LocationManager locationManager;
    private TextView textViewProvider;
    private TextView textViewLatitude;
    private TextView textViewLongitude;

    private boolean isReceivingLocationUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isReceivingLocationUpdates = false;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        textViewProvider = (TextView) findViewById(R.id.activity_main_textview_provider);
        textViewLatitude = (TextView) findViewById(R.id.activity_main_textview_latitude);
        textViewLongitude = (TextView) findViewById(R.id.activity_main_textview_longitude);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_main_get_location) {
            if (isReceivingLocationUpdates) {
                stopLocationUpdates();
            } else {
                receiveLocationUpdates();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void receiveLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        isReceivingLocationUpdates = true;
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(this);
        isReceivingLocationUpdates = false;
    }

    @Override
    public void onLocationChanged(final Location location) {
        textViewProvider.setText(getString(R.string.provider, location.getProvider()));
        textViewLatitude.setText(getString(R.string.latitude, location.getLatitude()));
        textViewLongitude.setText(getString(R.string.longitude, location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

}
