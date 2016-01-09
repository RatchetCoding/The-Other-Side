package de.ratchetcoding.theotherside;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;

public class GPSService extends Service implements LocationListener {

    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean canGetLocation;

    private Location location;
    private LatLng currentLocation;

    private Context context;

    private LocationManager locationManager;

    public GPSService(Context context) {
        this.context = context;
    }

    public LatLng getPos() {

            try {
                locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if (!isGPSEnabled && !isNetworkEnabled) {

                } else {
                    this.canGetLocation = true;

                    if (isNetworkEnabled) {
                        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this,null);
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location!= null)
                            currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    }
                    if(isGPSEnabled){
                        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this,null);
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(location!= null)
                            currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }


        return currentLocation;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
