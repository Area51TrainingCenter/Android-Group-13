package pe.area51.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private final static int REQUEST_MOCK_LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, MockLoginActivity.class), REQUEST_MOCK_LOGIN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView resultTextView = (TextView) findViewById(R.id.activity_main_textview_result);
        if (requestCode == REQUEST_MOCK_LOGIN) {
            //RESULT_OK y RESULT_CANCELLED son respuestas estándar que suelen enviarse cuando el resultado es positivo o el usuario cancela la operación respectivamente.
            if (resultCode == RESULT_OK) {
                final String username = data.getStringExtra(MockLoginActivity.EXTRA_RESULT_USERNAME);
                final String password = data.getStringExtra(MockLoginActivity.EXTRA_RESULT_PASSWORD);
                resultTextView.setText(getString(R.string.username, username) + "\n" + getString(R.string.password, password));
            } else {
                resultTextView.setText(R.string.result_cancelled);
            }
        }
    }
}
