package pe.area51.intentserviceapp;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

    private final static String SERVICE_NAME = "MyIntentService";
    public final static String INTENT_SERVICE_ACTION = "pe.area51.intentserviceapp.LONG_TASK_FINISHED";

    public MyIntentService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        longTask();
        sendBroadcast(new Intent(INTENT_SERVICE_ACTION));
    }

    //Dormimos el hilo para simular la ejecución de una tarea larga (por ejemplo la descarga de un archivo de Internet).
    //Recuerden que el Intent service abre su propio hilo de ejecución, por lo que esta tarea no afectará al hilo principal (UI Thread).
    private void longTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
