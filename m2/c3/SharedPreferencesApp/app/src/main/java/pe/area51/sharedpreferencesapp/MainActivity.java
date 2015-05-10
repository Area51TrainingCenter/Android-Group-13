package pe.area51.sharedpreferencesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public final static String SHARED_PREFERENCES_NAME = "pe.area51.sharedpreferencesapp.VISITORS";
    public final static String SHARED_PREFERENCES_LAST_VISITOR_KEY = "last_visitor";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView lastVisitorTextView = (TextView) findViewById(R.id.activity_main_textview_last_visitor);

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        final String lastVisitorName = sharedPreferences.getString(SHARED_PREFERENCES_LAST_VISITOR_KEY, null);

        if(lastVisitorName != null){
            lastVisitorTextView.setText(getString(R.string.last_visitor, lastVisitorName));
        }else{
            lastVisitorTextView.setText(getString(R.string.first_visitor));
        }
        
        findViewById(R.id.activity_main_button_save_visitor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = ((EditText) findViewById(R.id.activity_main_edittext_name)).getText().toString();
                lastVisitorTextView.setText(getString(R.string.last_visitor, name));
                sharedPreferences.edit().putString(SHARED_PREFERENCES_LAST_VISITOR_KEY, name).commit();
            }
        });

    }

}
