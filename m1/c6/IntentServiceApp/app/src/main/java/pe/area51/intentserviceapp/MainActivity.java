package pe.area51.intentserviceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    BroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View.OnClickListener myOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentService();
            }
        };
        final Button startIntentServiceButton = (Button) findViewById(R.id.activity_main_button_send_intent);
        startIntentServiceButton.setOnClickListener(myOnclickListener);
    }

    private void startIntentService() {
        Toast.makeText(MainActivity.this, getString(R.string.intent_service_started), Toast.LENGTH_SHORT).show();
        final Intent intent = new Intent(this, MyIntentService.class);
        //De la misma forma que iniciamos Activities, iniciaremos este servicio.
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Creamos el Broadcast Receiver.
        myBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this, getString(R.string.intent_service_finished), Toast.LENGTH_SHORT).show();
            }
        };
        //Creamos el IntentFilter y le agregamos los "action" de los Intent que podrá capturar este BroadcastReceiver.
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntentService.INTENT_SERVICE_ACTION);
        //Registramos el BroadcastReceiver con su IntentFilter.
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Quitarmos del registro al BroadcastReceiver, ya que no nos interesa recibir notificaciones si no estamos mirando el Activity.
        unregisterReceiver(myBroadcastReceiver);
    }
}
