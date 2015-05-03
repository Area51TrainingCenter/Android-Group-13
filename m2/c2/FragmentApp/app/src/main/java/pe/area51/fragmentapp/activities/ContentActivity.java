package pe.area51.fragmentapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import pe.area51.fragmentapp.R;
import pe.area51.fragmentapp.fragments.ContentFragment;
import pe.area51.fragmentapp.models.Message;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        final String messageTitle = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE_TITLE);
        final String messageContent = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE_CONTENT);
        ((ContentFragment) getFragmentManager().findFragmentById(R.id.activity_content_fragment_content)).setMessage(new Message(messageContent, messageTitle));
    }
}
