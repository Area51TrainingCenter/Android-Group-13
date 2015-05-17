package pe.area51.accelerometerapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    TextView textViewX;
    TextView textViewY;
    TextView textViewZ;

    SensorManager sensorManager;

    boolean isSensorListenerRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewX = (TextView) findViewById(R.id.activity_main_textview_x_axis);
        textViewY = (TextView) findViewById(R.id.activity_main_textview_y_axis);
        textViewZ = (TextView) findViewById(R.id.activity_main_textview_z_axis);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSensorListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterSensorListener();
    }

    private void registerSensorListener() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        isSensorListenerRegistered = true;
    }

    private void unregisterSensorListener() {
        sensorManager.unregisterListener(this);
        isSensorListenerRegistered = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_main_sensor_switch) {
            if (isSensorListenerRegistered) {
                registerSensorListener();
            } else {
                unregisterSensorListener();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            final float x = event.values[0];
            final float y = event.values[1];
            final float z = event.values[2];

            textViewX.setText(getString(R.string.accelerometer_x_axis, String.valueOf(x)));
            textViewY.setText(getString(R.string.accelerometer_y_axis, String.valueOf(y)));
            textViewZ.setText(getString(R.string.accelerometer_z_axis, String.valueOf(z)));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
