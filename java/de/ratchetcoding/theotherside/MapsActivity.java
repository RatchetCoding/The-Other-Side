package de.ratchetcoding.theotherside;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity{

    private GoogleMap map;
    LatLng position;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public MapsActivity(GoogleMap googleMap) {
        map = googleMap;
    }


    public void moveCamera(LatLng location) {
        if(location != null){
            position = location;
        }else{
            position = new LatLng(10,10);
        }
        map.addMarker(new MarkerOptions().position(location).title("Here you are.."));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location,12));
    }

    public void showOtherSide(LatLng location){

        LatLng otherSide = new LatLng((-1)*location.latitude,180+location.longitude);
        map.addMarker(new MarkerOptions().position(otherSide).title("This is the other side.."));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(otherSide,8));

    }
}
