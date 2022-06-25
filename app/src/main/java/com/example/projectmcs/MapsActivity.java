package com.example.projectmcs;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectmcs.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private ActivityMapsBinding binding;
    private Double longitude , latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        longitude = getIntent().getDoubleExtra("longitude",0);
        latitude = getIntent().getDoubleExtra("latitude",0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitude, longitude);

        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 40));

        gMap.addMarker(new MarkerOptions().position(latLng).title("We Are Here"));
    }
}