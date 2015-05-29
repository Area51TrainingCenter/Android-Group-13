package pe.area51.sqlitecontentproviderapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private final static Uri NOTES_URI = Uri.parse("content://pe.area51.sqlitecontentproviderapp.NotepadProvider/notes");
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.activity_main_textview_notes);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_add_note:
                final ContentValues contentValues = new ContentValues();
                contentValues.put("content", "Test content");
                contentValues.put("title", "Test title");
                getContentResolver().insert(NOTES_URI, contentValues);
                return true;
            case R.id.menu_main_delete_notes:
                getContentResolver().delete(NOTES_URI, null, null);
                return true;
            case R.id.menu_main_query_notes:
                textView.setText("");
                final Cursor cursor = getContentResolver().query(NOTES_URI, new String[]{"_id", "content", "title"}, null, null, null);
                while (cursor.moveToNext()) {
                    final String id = cursor.getString(cursor.getColumnIndex("_id"));
                    final String content = cursor.getString(cursor.getColumnIndex("content"));
                    final String title = cursor.getString(cursor.getColumnIndex("title"));
                    textView.append(id + " - " + title + " - " + content + "\n");
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
