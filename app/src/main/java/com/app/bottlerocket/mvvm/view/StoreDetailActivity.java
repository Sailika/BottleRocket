package com.app.bottlerocket.mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.bottlerocket.R;
import com.app.bottlerocket.databinding.StoreDetailActivityBinding;
import com.app.bottlerocket.mvvm.Constants;
import com.app.bottlerocket.mvvm.model.Store;
import com.app.bottlerocket.mvvm.viewmodel.StoreDetailViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreDetailActivity extends AppCompatActivity implements OnMapReadyCallback {


    private StoreDetailActivityBinding storeDetailActivityBinding;

    private GoogleMap mMap;
    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.store_detail_activity);
        setSupportActionBar(storeDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
        initializeMap();
    }

    //if play services available display the google map on ui or else change the views visibility to GONE...
    private void initializeMap() {
        if (isGooglePlayServicesAvailable()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            storeDetailActivityBinding.mapCardView.setVisibility(View.GONE);
        }
    }

    //check if google play service is available on the running device...
    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    //Method call to launch the Detail Activity
    public static Intent launchDetail(Context context, Store store) {
        Intent intent = new Intent(context, StoreDetailActivity.class);
        intent.putExtra(Constants.STORE_DATA, store);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        store = (Store) getIntent().getSerializableExtra(Constants.STORE_DATA);
        StoreDetailViewModel storeDetailViewModel = new StoreDetailViewModel(store);
        storeDetailActivityBinding.setStoreDetailViewModel(storeDetailViewModel);
        setTitle(store.getName());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setMarkerFromLocation(
                new LatLng(Double.parseDouble(store.getLatitude()), Double.parseDouble(store.getLongitude())));
    }

    private void setMarkerFromLocation(LatLng latLng) {

        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title(Constants.STORE_HERE);

        //Add the created marker on map/..
        mMap.addMarker(markerOptions);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(Constants.FIFTEEN).build();
        //animate the map to move to the marked location...
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }
}
