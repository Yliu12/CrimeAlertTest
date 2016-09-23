package edu.bsu.yliu12.crimealerttest;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.Manifest;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivityTest extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG="MapsActivityTest";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //API_KEY
        // AIzaSyB1xoVID79Fvjqi5H_rTapJ_SCFk1tQ9Sk
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreat Started");

        setContentView(R.layout.activity_maps_activity_test);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
        mMap = googleMap;

        //call request permission alert
        ActivityCompat.requestPermissions( this,new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION },
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

        int tmp = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        Log.d(TAG, "C)"+tmp);

        if (tmp == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Location permission granted");

            mMap.setMyLocationEnabled(true);
            Log.d(TAG, "LocationEnabled");

        } else {
            // Show rationale and request permission.
            Log.d(TAG, "Location permission not granted");
        }

        // Add a marker in My Home
        LatLng yliuHome = new LatLng(37.522, 122.1);
        mMap.addMarker(new MarkerOptions().position(yliuHome).title("Marker in yliuHome"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yliuHome));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
      //TODO impliment permission result handling
        /*  if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }*/
    }
}
