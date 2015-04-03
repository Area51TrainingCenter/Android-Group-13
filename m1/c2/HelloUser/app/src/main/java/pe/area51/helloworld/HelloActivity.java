package pe.area51.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        //Como sabemos que esta actividad siempre se inicia desde la actividad "MainActivity",
        //podemos obtener con seguridad el valor del nombre, ya que "MainActivity" siempre lo envía al iniciar esta actividad.
        String name = getIntent().getStringExtra(MainActivity.NAME_EXTRA);

        TextView textView = (TextView) findViewById(R.id.activity_hello_textview_hello);
        //Obtenemos la cadena cuyo ID sea "hello_name" y reemplazamos su expresión regular "%s" con el valor de la cadena "name".
        final String message = getString(R.string.hello_name, name);
        //Establecemos la cadena "message" en el TextView.
        textView.setText(message);
    }
}
