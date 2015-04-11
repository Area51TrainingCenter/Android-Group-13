package pe.area51.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    //Este método se llamará cada vez que se reciba un broadcast.
    //Los broadcast se reciben en un objeto intent.
    //Sólo se recibirán aquellos cuyo "action" hemos declarado en el intent filter de este broadcast receiver declarado en el Manifest.
    @Override
    public void onReceive(Context context, Intent intent) {
        //El nombre del broadcast se encuentra en el atributo "action" del intent. Obtenemos su valor y lo escribimos en el Logcat.
        Log.d(TAG, intent.getAction());
        switch (intent.getAction()) {
            case LocationManager.PROVIDERS_CHANGED_ACTION:
                break;
            case MainActivity.MY_BROADCAST:
                //Este broadcast tiene un dato extra en el intent. Este dato lo pusimos al momento de disparar el broadcast en la clase MainActivity.
                Log.d(TAG, intent.getStringExtra(MainActivity.MY_INTENT_EXTRA));
                break;
        }
    }

}