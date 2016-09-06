package com.accenture.kdc.minhonoh;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Gps extends FragmentActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener{

	 /**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    // These settings are the same as the settings for the map. They will in fact give you updates
    // at the maximal rates currently possible.
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    
    private double latitude;
    private double longitude;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);
        setUpMapIfNeeded();
        setUpGoogleApiClientIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        setUpGoogleApiClientIfNeeded();
        mGoogleApiClient.connect();
    }

    private void setUpGoogleApiClientIfNeeded() {
    	 if (mGoogleApiClient == null) {
             mGoogleApiClient = new GoogleApiClient.Builder(this)
                     .addApi(LocationServices.API)
                     .addConnectionCallbacks(this)
                     .addOnConnectionFailedListener(this)
                     .build();
         }
	}

	@Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

	//GoogleMap 객체 생성
    private void setUpMapIfNeeded() {
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }

    //지도에 현재위치 표시하기
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Here!!"));
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
    }

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                REQUEST,
                this);  // LocationListener
		
		//현재위치 가져오기
		latitude = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLatitude();
		longitude = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLongitude();
		
		//지도에 그리기
		if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
			if (mMap != null) {
	             setUpMap();
	        }	
		}
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}
 
}
