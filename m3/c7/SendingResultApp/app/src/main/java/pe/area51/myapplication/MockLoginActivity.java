package pe.area51.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MockLoginActivity extends Activity {

    public static final String EXTRA_RESULT_USERNAME = "pe.area51.myapplication.RESULT_USERNAME";
    public static final String EXTRA_RESULT_PASSWORD = "pe.area51.myapplication.RESULT_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mock_login);
        findViewById(R.id.mock_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = ((TextView) findViewById(R.id.mock_login_edittext_username)).getText().toString();
                final String password = ((TextView) findViewById(R.id.mock_login_edittext_password)).getText().toString();
                //Este método sirve para establecer el resultado a enviar al finalizar la actividad.
                setResult(RESULT_OK, new Intent().putExtra(EXTRA_RESULT_USERNAME, userName).putExtra(EXTRA_RESULT_PASSWORD, password));
                finish();
            }
        });
    }
}
