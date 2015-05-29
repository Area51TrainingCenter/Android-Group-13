package pe.area51.sqlitecontentproviderapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;

public class SQLiteManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "unencrypted-notes-database";
    public static final int DATABASE_VERSION = 1;
    public static final String NOTES_TABLE = "notes";

    public SQLiteManager(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE " + NOTES_TABLE + " (_id INTEGER PRIMARY KEY, title TEXT, content TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //No es necesario utilizar este método si se va acceder a los datos a través del Content Provider, sólo si se va a acceder a los datos directamente desde esta clase.
    public Collection<Note> getNotes() {
        final Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + NOTES_TABLE, null);
        final Collection<Note> notes = new ArrayList<>();
        //Recordar que el puntero del cursor empieza en una posición anterior al primer elemento.
        while (cursor.moveToNext()) {
            final long id = cursor.getLong(cursor.getColumnIndex("_id"));
            final String title = cursor.getString(cursor.getColumnIndex("title"));
            final String content = cursor.getString(cursor.getColumnIndex("content"));
            notes.add(new Note(id, title, content));
        }
        cursor.close();
        return notes;
    }
}
