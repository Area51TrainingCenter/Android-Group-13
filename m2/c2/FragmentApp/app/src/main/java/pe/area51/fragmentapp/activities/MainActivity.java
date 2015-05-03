package pe.area51.fragmentapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import pe.area51.fragmentapp.R;
import pe.area51.fragmentapp.fragments.ContentFragment;
import pe.area51.fragmentapp.fragments.ListFragment;
import pe.area51.fragmentapp.models.Message;


public class MainActivity extends Activity implements ListFragment.ListFragmentInterface {

    public static final String EXTRA_MESSAGE_TITLE = "pe.area51.fragmentapp.activities.EXTRA_MESSAGE_TITLE";
    public static final String EXTRA_MESSAGE_CONTENT = "pe.area51.fragmentapp.activities.EXTRA_MESSAGE_CONTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ListFragment) getFragmentManager().findFragmentById(R.id.activity_main_fragment_list)).setListFragmentInterface(this);
    }

    @Override
    public void onMessageSelected(final Message message) {
        //Para estos escenarios, donde los fragments varían, es mejor utilizar directamente el fragment manager en vez de declarar los fragments en los archivos de layout. Fuente: http://developer.android.com/training/basics/fragments/creating.html#AddInLayout
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            startActivity(new Intent(this, ContentActivity.class).putExtra(EXTRA_MESSAGE_TITLE, message.getTitle()).putExtra(EXTRA_MESSAGE_CONTENT, message.getContent()));
        } else {
            ((ContentFragment) getFragmentManager().findFragmentById(R.id.activity_main_fragment_content)).setMessage(message);
        }
    }
}
