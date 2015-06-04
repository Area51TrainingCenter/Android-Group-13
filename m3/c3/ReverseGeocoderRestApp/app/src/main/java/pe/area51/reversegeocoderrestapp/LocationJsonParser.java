package pe.area51.reversegeocoderrestapp;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationJsonParser {

    public static Location parse(final String json) throws JSONException {
        final JSONObject response = new JSONObject(json);
        final String latitude = response.getString("lat");
        final String longitude = response.getString("lon");
        final String locationName = response.getString("display_name");
        final JSONObject address = response.getJSONObject("address");
        final String country = address.getString("country");
        return new Location(Double.valueOf(latitude), Double.valueOf(longitude), locationName, country);
    }

}
