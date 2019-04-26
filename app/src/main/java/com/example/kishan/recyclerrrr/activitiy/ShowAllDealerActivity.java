package com.example.kishan.recyclerrrr.activitiy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kishan.recyclerrrr.BuildConfig;
import com.example.kishan.recyclerrrr.modelClass.addagentbyid.GetDealerByIdResponse;
import com.example.kishan.recyclerrrr.adapter.DealerAdapter;
import com.example.kishan.recyclerrrr.R;
import com.example.kishan.recyclerrrr.retrofit.RestClient;
import com.example.kishan.recyclerrrr.utils.AttandancePrefs;
import com.example.kishan.recyclerrrr.utils.Utils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllDealerActivity extends AppCompatActivity {
    private List<GetDealerByIdResponse> dealerList = new ArrayList<GetDealerByIdResponse>();
    private RecyclerView recyclerView;


    private Toolbar toolbar;

    private String mLastUpdateTime;
    private static final String TAG = ShowAllDealerActivity.class.getSimpleName();
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final int REQUEST_CHECK_SETTINGS = 100;

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    int agentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        agentId = AttandancePrefs.getInt(getApplicationContext(), "agentId", 0);
        restoreValuesFromBundle(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


    }


    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

            }
        };

        mRequestingLocationUpdates = false;
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();

    }

    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }


    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        if (mCurrentLocation != null) {
                            Toast.makeText(getApplicationContext(), "Lat: " + mCurrentLocation.getLatitude()
                                    + ", Lng: " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Last known location is not available!", Toast.LENGTH_SHORT).show();
                        }


                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());


                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "GpsTracker settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {

                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(ShowAllDealerActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "GpsTracker settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(ShowAllDealerActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }


                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");

                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    public void startLocationButtonClick() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        getAllDealers();

    }

    private void getAllDealers() {
        GetDealerByIdResponse requestModel = new GetDealerByIdResponse();
        requestModel.setAgentId(agentId);
        Utils.showProgressDialog(this);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.getAgentById(agentId, new Callback<List<GetDealerByIdResponse>>() {
                @Override
                public void onResponse(Call<List<GetDealerByIdResponse>> call, Response<List<GetDealerByIdResponse>> response) {
                    Utils.dismissProgressDialog();

                    //  Toast.makeText(ShowAllDealerActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    if (response.body() != null) {
                        Log.d("First", "Done1");
                        dealerList = response.body();
                        Log.d("First", "Done2");
                        if (dealerList != null && dealerList.size() > 0) {
                            Log.d("First", "Done3");
                            DealerAdapter dealerAdapter = new DealerAdapter(getApplicationContext());
                            dealerAdapter.setdata(dealerList);
                            Log.d("First", "Done4");

                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(dealerAdapter);


                        }


                    }
                }

                @Override
                public void onFailure(Call<List<GetDealerByIdResponse>> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(ShowAllDealerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String msg = "";
        switch (item.getItemId()) {
            case R.id.add_contact:

                Intent intent = new Intent(ShowAllDealerActivity.this, AddDealerActivity.class);
                startActivity(intent);
                break;
            case R.id.mark_attendence:
                startLocationButtonClick();
                break;
        }
        Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


    public void sendMessage(View view) {

        EditText email = findViewById(R.id.ET_EMAIL);
        EditText firstName = findViewById(R.id.Et_NAME);
        EditText mobile = findViewById(R.id.ET_MOBILE);

        String emailDealer = email.getText().toString();
        String firstNameDealer = firstName.getText().toString();
        String mobileDealer = mobile.getText().toString();


        Intent intent = new Intent(this, AddDealerActivity.class);
        intent.putExtra("EXTRA_MESSAGE", emailDealer);
        intent.putExtra("EXTRA_MESSAGE1", firstNameDealer);
        intent.putExtra("EXTRA_MESSAGE2", mobileDealer);


        startActivity(intent);

    }

}