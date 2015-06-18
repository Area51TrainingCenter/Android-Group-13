package pe.area51.parcelableresultproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private final String name;
    private final String lastName;
    private final int age;

    public Person(final Parcel source) {
        this.name = source.readString();
        this.lastName = source.readString();
        this.age = source.readInt();
    }

    public Person(final String name, final String lastName, final int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeInt(age);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(final Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(final int size) {
            return new Person[size];
        }
    };
}
