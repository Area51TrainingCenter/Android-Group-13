package pe.area51.googlemapsproject;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MyMapFragment extends MapFragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationChangeListener {
    GoogleMap googleMap;

    private boolean cameraUpdated = false;

    public void setMapType(int mapType) {
        if (googleMap != null) {
            googleMap.setMapType(mapType);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMapAsync(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Este método se dispara cuando el mapa está listo para ser utilizado. Se pasa un objeto referenciando a nuestro mapa como parámetro.
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationChangeListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        //Este método se dispara cuando presionamos el botón para obtener la ubicación actual.
        cameraUpdated = false;
        Toast.makeText(getActivity(), R.string.getting_location,
                Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationChange(Location location) {
        //Este método se dispara cada vez que se obtiene una ubicación nueva.
        Toast.makeText(
                getActivity(),
                getString(R.string.my_location_is, location.getLatitude(),
                        location.getLongitude()), Toast.LENGTH_SHORT).show();
        if (!cameraUpdated) {
            updateCamera(location);
        }
    }

    private void updateCamera(Location location) {
        cameraUpdated = true;
        LatLng latLng = new LatLng(location.getLatitude(),
                location.getLongitude());
        CameraPosition cameraPosition = CameraPosition.builder().target(latLng)
                .zoom(20).bearing(70).tilt(40).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newCameraPosition(cameraPosition);
        // animateCamera() desplaza suavemente la cámara hacia una ubicación, mientras que moveCamera() la desplaza instantáneamente.
        googleMap.animateCamera(cameraUpdate);
    }
}
