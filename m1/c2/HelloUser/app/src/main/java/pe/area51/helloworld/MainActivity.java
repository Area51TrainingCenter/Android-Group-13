package pe.area51.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    public final static String NAME_EXTRA = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Establece el layout "activity_main" para esta actividad.
        setContentView(R.layout.activity_main);

        //Obtenemos la referencia a nuestro botón, sabemos que su ID es "activity_main_button_send".
        //Como sabemos que es un botón, hacemos la conversión.
        Button button = (Button) findViewById(R.id.activity_main_button_send);
        //Establecemos la acción a realizar cuando hagamos click en el botón.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos la referencia a nuestro EditText, sabemos que su ID es "activity_main_edittext_name".
                //Hacemos igualmente la conversión a "EditText", ya que el método "findViewById" devuelve objetos del tipo "View".
                EditText editText = (EditText) findViewById(R.id.activity_main_edittext_name);
                //Obtenemos el texto del EditText.
                String name = editText.getText().toString();
                //Creamos el Intent para ejecutar la actividad "HelloActivity".
                //Este constructor nos pide una referencia del objeto "Context" y la clase de destino.
                //La referencia del objeto "Context" la tenemos en nuestra actividad, por lo que la pasamos como argumento.
                //Recuerden que la clase "Activity" hereda de la clase "Context".
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);
                //Almacenamos en el Intent el nombre que queremos transferir.
                intent.putExtra(NAME_EXTRA, name);
                startActivity(intent);
            }
        });
    }

    //Este método crea el menú, recuerden que el menú se almacena como recurso.
    //Pueden ver el menú en la carpeta "menu" dentro de la carpeta de recursos.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Este método se llama cuando hacemos click a un botón del menú.
    //Debemos diferenciar a qué botón hemos hecho click.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_item_example_1:
                //Mostramos un "Toast" con un texto de ejemplo.
                Toast.makeText(this, getString(R.string.menu_example_1), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_main_item_example_2:
                Toast.makeText(this, getString(R.string.menu_example_2), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
