package pe.area51.notepad.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import pe.area51.notepad.R;
import pe.area51.notepad.adapters.NoteArrayAdapter;
import pe.area51.notepad.dialogs.AddNoteDialogFragment;
import pe.area51.notepad.dialogs.ClearListDialogFragment;
import pe.area51.notepad.fragments.ContentFragment;
import pe.area51.notepad.fragments.ListFragment;
import pe.area51.notepad.models.Note;
import pe.area51.notepad.provider.NotesContract;


public class MainActivity extends Activity implements AddNoteDialogFragment.OnNoteCreatedListener, ClearListDialogFragment.OnClearListListener, AdapterView.OnItemClickListener {

    public static final String EXTRA_NOTE = "pe.area51.fragmentapp.activities.EXTRA_NOTE";

    private NoteArrayAdapter noteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ListFragment listFragment = ((ListFragment) getFragmentManager().findFragmentById(R.id.activity_main_fragment_list));
        noteArrayAdapter = new NoteArrayAdapter(listFragment, getNotes());
        listFragment.getListView().setAdapter(noteArrayAdapter);
        listFragment.getListView().setOnItemClickListener(this);
    }

    private List<Note> getNotes() {
        final Cursor cursor = getContentResolver().query(NotesContract.URI, null, null, null, null);
        final List<Note> notes = new ArrayList<>();
        //Recordar que el puntero del cursor empieza en una posición anterior al primer elemento.
        while (cursor.moveToNext()) {
            final long id = cursor.getLong(cursor.getColumnIndex(NotesContract.ID));
            final String title = cursor.getString(cursor.getColumnIndex(NotesContract.TITLE));
            final String content = cursor.getString(cursor.getColumnIndex(NotesContract.CONTENT));
            final long creationTime = cursor.getLong(cursor.getColumnIndex(NotesContract.CREATION_TIME));
            final long updateTime = cursor.getLong(cursor.getColumnIndex(NotesContract.UPDATE_TIME));
            notes.add(new Note(id, title, content, creationTime, updateTime));
        }
        cursor.close();
        return notes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_add_message:
                final AddNoteDialogFragment addNoteDialogFragment = new AddNoteDialogFragment();
                addNoteDialogFragment.setOnNoteCreatedListener(this);
                addNoteDialogFragment.show(getFragmentManager(), "add_message_dialog_fragment");
                return true;
            case R.id.menu_main_clear_list:
                final ClearListDialogFragment clearListDialogFragment = new ClearListDialogFragment();
                clearListDialogFragment.setOnClearListListener(this);
                clearListDialogFragment.show(getFragmentManager(), "clear_list_dialog_fragment");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteCreated(final Note note) {
        //Agregar primero a la base de datos.
        final ContentValues noteContentValues = new ContentValues();
        noteContentValues.put(NotesContract.TITLE, note.getTitle());
        noteContentValues.put(NotesContract.CONTENT, note.getContent());
        noteContentValues.put(NotesContract.CREATION_TIME, note.getCreationTime());
        noteContentValues.put(NotesContract.UPDATE_TIME, note.getUpdateTime());
        final long id = Integer.valueOf(getContentResolver().insert(NotesContract.URI, noteContentValues).getPathSegments().get(1));
        //Crear una nueva nota con el ID.
        noteArrayAdapter.add(new Note(id, note.getTitle(), note.getContent(), note.getCreationTime(), note.getUpdateTime()));
    }

    @Override
    public void onClearList() {
        getContentResolver().delete(NotesContract.URI, null, null);
        noteArrayAdapter.clear();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Para estos escenarios, donde los fragments varían, es mejor utilizar directamente el fragment manager en vez de declarar los fragments en los archivos de layout. Fuente: http://developer.android.com/training/basics/fragments/creating.html#AddInLayout
        final Note note = (Note) parent.getItemAtPosition(position);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            startActivity(new Intent(this, ContentActivity.class)
                    .putExtra(EXTRA_NOTE, note));
        } else {
            ((ContentFragment) getFragmentManager().findFragmentById(R.id.activity_main_fragment_content)).setNote(note);
        }
    }
}
