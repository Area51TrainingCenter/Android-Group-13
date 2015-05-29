package pe.area51.sqlitecontentproviderapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class NotesContentProvider extends ContentProvider {

    public static final Uri URI = Uri.parse("content://pe.area51.sqlitecontentproviderapp.NotepadProvider");

    private SQLiteManager sqLiteManager;

    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(URI.getAuthority(), "notes", NOTES);
        uriMatcher.addURI(URI.getAuthority(), "notes/#", NOTES_ID);
    }

    public static final String MIME_ITEM = "vnd.android.cursor.item/vnd.pe.area51.notepad.note";
    public static final String MIME_DIR = "vnd.android.cursor.dir/vnd.pe.area51.notepad.note";

    @Override
    public boolean onCreate() {
        sqLiteManager = new SQLiteManager(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) != UriMatcher.NO_MATCH) {
            if (uriMatcher.match(uri) == NOTES_ID) {
                selection = "_id=" + uri.getLastPathSegment();
            }
            return sqLiteManager.getReadableDatabase().query(SQLiteManager.NOTES_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case NOTES_ID:
                return MIME_ITEM;
            case NOTES:
                return MIME_DIR;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != UriMatcher.NO_MATCH) {
            final long id = sqLiteManager.getWritableDatabase().insert(SQLiteManager.NOTES_TABLE, null, values);
            return ContentUris.withAppendedId(URI, id);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (uriMatcher.match(uri) != UriMatcher.NO_MATCH) {
            if (uriMatcher.match(uri) == NOTES_ID) {
                selection = "_id=" + uri.getLastPathSegment();
            }
            return sqLiteManager.getWritableDatabase().delete(SQLiteManager.NOTES_TABLE, selection, selectionArgs);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (uriMatcher.match(uri) != UriMatcher.NO_MATCH) {
            if (uriMatcher.match(uri) == NOTES_ID) {
                selection = "_id=" + uri.getLastPathSegment();
            }
            return sqLiteManager.getWritableDatabase().update(SQLiteManager.NOTES_TABLE, values, selection, selectionArgs);
        }
        return 0;
    }
}
