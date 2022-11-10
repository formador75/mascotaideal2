package com.miempresa.mascotaideal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String ciudad;
    double latitud,longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapslayout);

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("ciudad");
        latitud = extras.getDouble("latitud");
        longitud = extras.getDouble("longitud");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
      mMap = googleMap;
        LatLng ciudad = new LatLng(latitud,longitud);
        mMap.addMarker(new MarkerOptions().position(ciudad).title("Marker in "+ ciudad +""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ciudad));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );


    }
}