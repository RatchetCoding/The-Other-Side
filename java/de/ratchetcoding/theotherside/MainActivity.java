package de.ratchetcoding.theotherside;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleApiClient mGoogleApiClient;
    private SupportMapFragment mapFrag;
    private MapsActivity mapActivity;
    private GPSService gpsService;
    private LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpsService = new GPSService(MainActivity.this);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        setLocation();
    }

    private void setLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && gpsService != null) {
            location = gpsService.getPos();
        }
        if(location != null && mapActivity != null) {
            mapActivity.moveCamera(location);
        }else{
            Toast.makeText(MainActivity.this, "Couldn't determine location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();


    }
    public void goToOtherSide(View view){
        mapActivity.showOtherSide(location);

    }

    @Override
    public void onMapReady (GoogleMap googleMap) {
        mapActivity = new MapsActivity(googleMap);
    }

}
