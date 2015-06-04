package pe.area51.reversegeocoderrestapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity {

    public final static String REVERSE_GEOCODING_API_URL = "http://nominatim.openstreetmap.org/reverse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_button_get_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String latitude = URLEncoder.encode(((EditText) findViewById(R.id.activity_main_edittext_latitude)).getText().toString(), "utf-8");
                    final String longitude = URLEncoder.encode(((EditText) findViewById(R.id.activity_main_edittext_longitude)).getText().toString(), "utf-8");
                    //Documentación del API: http://wiki.openstreetmap.org/wiki/Nominatim#Reverse_Geocoding_.2F_Address_lookup
                    final String url = REVERSE_GEOCODING_API_URL + "?format=json" + "&lat=" + latitude + "&lon=" + longitude;
                    new GetAddressRequestAsyncTask().execute(url);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    ;

    private class GetAddressRequestAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            final String url = params[0];
            try {
                return HttpConnection.doJsonHttpGet(url);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    final Location location = LocationJsonParser.parse(response);
                    Toast.makeText(MainActivity.this, location.getLocationName(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, R.string.invalid_server_response, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            }
        }
    }

}
