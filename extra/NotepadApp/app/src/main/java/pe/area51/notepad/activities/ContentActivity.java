package pe.area51.notepad.activities;

import android.app.Activity;
import android.os.Bundle;

import pe.area51.notepad.R;
import pe.area51.notepad.fragments.ContentFragment;
import pe.area51.notepad.models.Note;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        final Note note = getIntent().getParcelableExtra(MainActivity.EXTRA_NOTE);
        ((ContentFragment) getFragmentManager().findFragmentById(R.id.activity_content_fragment_content)).setNote(note);
    }
}
