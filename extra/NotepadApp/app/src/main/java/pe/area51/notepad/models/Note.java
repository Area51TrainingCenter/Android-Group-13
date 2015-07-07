package pe.area51.notepad.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private final long id;
    private final String title;
    private final String content;
    private final long creationTime;
    private final long updateTime;

    public Note(final Parcel source) {
        this.id = source.readLong();
        this.title = source.readString();
        this.content = source.readString();
        this.creationTime = source.readLong();
        this.updateTime = source.readLong();
    }

    public Note(final long id, final String title, final String content, final long creationTime, final long updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(final Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(final int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeLong(this.creationTime);
        dest.writeLong(this.updateTime);
    }
}
