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
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;


import java.util.ArrayList;
import java.util.List;

import static com.cwm.incube.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double area;
    List<LatLng> listLatLng = new ArrayList<>();
    List<Marker> listMarker = new ArrayList<>();
    Polyline polyline;
    Polygon polygon;

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
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        showCurrentLocation();
        addMarkerOnMapLongClick();
        MarkerOnClick();
        onMarkerDrag();
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
                if(polygon==null){
                    addMarker(latLng);
                }
            }
        });
    }

    private void clearPolyline(){
        if(polyline != null){
            polyline.remove();
        }
    }

    private void addMarker(LatLng latLng){
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latLng.latitude, latLng.longitude))
                .draggable(true));
        listLatLng.add(latLng);
        listMarker.add(marker);
        clearPolyline();
        polyline = mMap.addPolyline(new PolylineOptions().addAll(listLatLng));
    }

    private void MarkerOnClick(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            public boolean onMarkerClick(Marker marker) {
                if(listLatLng.get(0).equals(marker.getPosition())){
                    addPolygon();
                    computeArea();
                    return false;
                }else{
                    deleteMarker(marker);
                    return true;
                }
            }
        });
    }

    private void addPolygon(){
        mMap.clear();
        polyline.remove();
        PolygonOptions polygonOptions = new PolygonOptions();
        polygon = mMap.addPolygon(polygonOptions.addAll(listLatLng)
                .fillColor(Color.GREEN));
    }

    private void computeArea(){
        area = SphericalUtil.computeArea(listLatLng);
        Log.d("DebugTag", "Area: " + Double.toString(area));

    }

    private void deleteMarker(Marker marker){
        marker.remove();
        listLatLng.remove(marker.getPosition());
        listMarker.remove(marker);
        clearPolyline();
        polyline = mMap.addPolyline(new PolylineOptions().addAll(listLatLng));
    }

    private void onMarkerDrag(){
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            int index;

            public void onMarkerDragStart(Marker marker) {
                index = listMarker.indexOf(marker);
                drag(marker);
            }

            public void onMarkerDrag(Marker marker) {
                drag(marker);
            }

            public void onMarkerDragEnd(Marker marker) {
                drag(marker);
            }

            private void drag(Marker marker){
                listLatLng.set(index, marker.getPosition());
                clearPolyline();
                polyline = mMap.addPolyline(new PolylineOptions().addAll(listLatLng));
            }
        });
    }
}

