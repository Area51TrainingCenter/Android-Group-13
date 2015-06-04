package pe.area51.reversegeocoderrestapp;

public class Location {

    final private double latitude;
    final private double longitude;
    final private String locationName;
    final private String country;

    public Location(double latitude, double longitude, String locationName, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationName = locationName;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getCountry() {
        return country;
    }
}
