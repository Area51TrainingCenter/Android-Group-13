package pe.area51.broadcastreceivertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    public static final String MY_BROADCAST = "pe.area51.broadcastreceivertest.MY_BROADCAST_ACTION";
    public static final String MY_INTENT_EXTRA = "extra_value";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_fire_broadcast) {
            //Creamos el objeto Intent.
            final Intent intent = new Intent();
            //Le asignamos al atributo "action" del Intent el nombre de nuestro broadcast.
            //El nombre del broadcast DEBE estar en el atributo action del Intent.
            intent.setAction(MY_BROADCAST);
            //En este caso, estamos agregando un valor extra al Intent.
            //Tal como transferimos datos entre actividades pasando el Intent al método "startActivity", también podemos transferir datos enviando un Intent por broadcast.
            intent.putExtra(MY_INTENT_EXTRA, "¡Hola mundo!");
            //Enviamos el broadcast.
            sendBroadcast(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
