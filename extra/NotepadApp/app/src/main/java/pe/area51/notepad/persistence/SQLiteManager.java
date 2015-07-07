package pe.area51.notepad.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "unencrypted-notes-database";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTES = "notes";

    public static final String COLUMN_NOTE_ID = "_id";
    public static final String COLUMN_NOTE_TITLE = "title";
    public static final String COLUMN_NOTE_CONTENT = "content";
    public static final String COLUMN_NOTE_CREATION_TIME = "creation_time";
    public static final String COLUMN_NOTE_UPDATE_TIME = "update_time";

    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NOTES + "(" +
            COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOTE_TITLE + " TEXT, " +
            COLUMN_NOTE_CONTENT + " TEXT, " +
            COLUMN_NOTE_CREATION_TIME + " INTEGER, " +
            COLUMN_NOTE_UPDATE_TIME + " INTEGER " +
            ")";

    public SQLiteManager(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    }
}
