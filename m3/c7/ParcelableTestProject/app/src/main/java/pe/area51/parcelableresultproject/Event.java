package pe.area51.parcelableresultproject;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Event implements Parcelable {

    private final String name;
    private final long date;
    private final String description;
    private final Location location;
    private final Person author;
    private final Collection<Person> guests;

    public Event(final Parcel source) {
        this.name = source.readString();
        this.date = source.readLong();
        this.description = source.readString();
        this.location = source.readParcelable(Location.class.getClassLoader());
        this.author = source.readParcelable(Person.class.getClassLoader());
        final List<Person> guestsOutList = new ArrayList<>();
        source.readList(guestsOutList, Person.class.getClassLoader());
        this.guests = guestsOutList;
    }

    public Event(final String name, final long date, final String description, final Location location, final Person author, final Collection<Person> guests) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.location = location;
        this.author = author;
        this.guests = guests;
    }

    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public Person getAuthor() {
        return author;
    }

    public Collection<Person> getGuests() {
        return guests;
    }

    /*
    Este método debe devolver "0" o "1" (constante
    "Parcelable.CONTENTS_FILE_DESCRIPTOR"). Se utiliza la constante
    "CONTENTS_FILE_DESCRIPTOR" (o 1), cuando se implementa la interfaz
    "Parcelable" con la clase "FileDescriptor". En todos los otros casos,
    debe devolverse 0.
    */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name);
        dest.writeLong(date);
        dest.writeString(description);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(author, flags);
        final List<Person> guestsOutList = new ArrayList<>(guests);
        dest.writeList(guestsOutList);

    }

    /*
    El objeto "Creator" es necesario para recrear nuestro objeto
    a partir de un "Parcel". Debe crearse como público y estático.
    */
    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(final Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(final int size) {
            return new Event[size];
        }
    };
}
