package com.cwm.incube;

import android.graphics.Color;
import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.GoogleMap.OnPolygonClickListener;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static com.cwm.incube.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<LatLng> listLatLng = new ArrayList<LatLng>();
    PolylineOptions polylineOptions = new PolylineOptions();
    Polyline polyLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //polylineOptions.color(Color.RED);
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        showCurrentLocation();
        addMarkerOnMapLongClick();
        deleteMarkerOnClick();

        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(18.796627, 98.952543),
                        new LatLng(18.794991, 98.952528),
                        new LatLng(18.794984, 98.953248),
                        new LatLng(18.796456, 98.953225))
                .strokeWidth(3)
                .strokeColor(Color.argb(200, 0, 255, 0))
                .fillColor(Color.argb(128, 0, 255, 0));

        Polygon polygon = mMap.addPolygon(rectOptions);
        polygon.setClickable(true);

        mMap.setOnPolygonClickListener(new OnPolygonClickListener() {
            public void onPolygonClick(Polygon polygon) {
                polygon.setFillColor(Color.argb(50, 255, 0, 0));
            }
        });

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void showCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17));
            }
        }
    }

    private void addMarkerOnMapLongClick() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            public void onMapLongClick(LatLng latLng) {
                addMarker(latLng);
            }
        });
    }

    private void addMarker(LatLng latLng){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latLng.latitude, latLng.longitude))
                .draggable(true));
        listLatLng.add(latLng);
        polylineOptions.add(latLng);
        if(polyLine != null){
            polyLine.remove();
        }polyLine = mMap.addPolyline(polylineOptions);
    }

    private void deleteMarkerOnClick(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            public boolean onMarkerClick(Marker marker) {
                marker.remove();
                listLatLng.remove(marker.getPosition());
                return true;
            }
        });
    }
}
