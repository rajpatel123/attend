package com.example.kishan.recyclerrrr.activitiy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kishan.recyclerrrr.BuildConfig;
import com.example.kishan.recyclerrrr.modelClass.addDealer.AddDealerRequest;
import com.example.kishan.recyclerrrr.modelClass.addDealer.AddDealerResponse;
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

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDealerActivity extends AppCompatActivity {
    private EditText firstName, mobileNumber, email, message;
    private Button AddDealerButton;
    Double latitude;
    Double longitude;
    int agentId;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        startLocationUpdates();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddDealerActivity.this, ShowAllDealerActivity.class));
                finish();
            }
        });


        restoreValuesFromBundle(savedInstanceState);

        firstName = findViewById(R.id.firstname_ET);
        mobileNumber = findViewById(R.id.Mobile_ET);
        email = findViewById(R.id.email_ET);
        message = findViewById(R.id.message_ET);


        AddDealerButton = findViewById(R.id.reg_BTN);

        AddDealerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

            }
        });


        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        EditText editText = findViewById(R.id.email_ET);
        editText.setText(message);


        String message1 = intent.getStringExtra("EXTRA_MESSAGE1");
        EditText editText1 = findViewById(R.id.firstname_ET);
        editText1.setText(message1);


        String message2 = intent.getStringExtra("EXTRA_MESSAGE2");
        EditText editText2 = findViewById(R.id.Mobile_ET);
        editText2.setText(message2);


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
                                    rae.startResolutionForResult(AddDealerActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "GpsTracker settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(AddDealerActivity.this, errorMessage, Toast.LENGTH_LONG).show();
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


        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
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

    public boolean validate() {
        boolean check = true;

        String firstNameId = firstName.getText().toString();
        String mobileNumberId = mobileNumber.getText().toString();
        String emailId = email.getText().toString();
        String messageId = message.getText().toString();
        agentId = AttandancePrefs.getInt(getApplicationContext(), "agentId", 0);

        if (mCurrentLocation != null) {
            latitude = Double.valueOf(Double.toString(mCurrentLocation.getLatitude()));
            longitude = Double.valueOf(Double.toString(mCurrentLocation.getLongitude()));
        }
        if (firstNameId.isEmpty()) {
            firstName.setError("at least 3 characters");
            check = false;
        } else {
            firstName.setError(null);
        }

        if (mobileNumberId.isEmpty()) {
            mobileNumber.setError("Enter Valid lname");
            check = false;
        } else {
            mobileNumber.setError(null);
        }


        if (emailId.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            email.setError("enter a valid email address");
            check = false;
        } else {
            email.setError(null);
        }

        if (messageId.isEmpty()) {
            message.setError("Enter Valid Message");
            check = false;
        } else {
            message.setError(null);
        }


        if (check) {

            AddDealerRequest requestModel = new AddDealerRequest();
            requestModel.setEmail(emailId);
            requestModel.setAgentId(agentId);
            requestModel.setFirstName(firstNameId);
            requestModel.setLastName(mobileNumberId);
            requestModel.setLatitude(latitude);
            requestModel.setLongitude(longitude);
            requestModel.setMessage(messageId);
            Utils.showProgressDialog(this);

            RestClient.addNewDealer(requestModel, new Callback<AddDealerResponse>() {
                @Override
                public void onResponse(Call<AddDealerResponse> call, Response<AddDealerResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResult().equalsIgnoreCase("success")) {
                            Utils.displayToast(getApplicationContext(), "Successfuly ADD dealer");
                            Intent intent = new Intent(AddDealerActivity.this, ShowAllDealerActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AddDealerActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                        }
                    }

                }


                @Override
                public void onFailure(Call<AddDealerResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Utils.displayToast(AddDealerActivity.this, "Unable to register, please try again later");


                }
            });
        }
        return check;
    }
}

