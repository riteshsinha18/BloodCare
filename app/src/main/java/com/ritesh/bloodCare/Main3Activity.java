package com.ritesh.bloodCare;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        class SpinnerExample extends Main3Activity {

            private String[] arraySpinner;

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);


                this.arraySpinner = new String[]{
                        "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"
                };
                Spinner s = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, arraySpinner);
                s.setAdapter(adapter);
            }
        }

        class LbsGeocodingActivity extends Main3Activity {

            private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
            private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds


            protected LocationManager locationManager;
            protected Button retrieveLocationButton;

            @Override
            public void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                retrieveLocationButton = (Button) findViewById(R.id.button);

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MINIMUM_TIME_BETWEEN_UPDATES,
                        MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                        new MyLocationListener()
                );
                retrieveLocationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCurrentLocation();
                    }
                });

            }

            protected void showCurrentLocation() {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    String message = String.format(
                            "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                            location.getLongitude(), location.getLatitude()
                    );
                    Toast.makeText(LbsGeocodingActivity.this, message,
                            Toast.LENGTH_LONG).show();

                }
            }

            class MyLocationListener implements LocationListener {
                public void onLocationChanged(Location location) {
                    String message = String.format(
                            "New Location \n Longitude: %1$s \n Latitude: %2$s",
                            location.getLongitude(), location.getLatitude()
                    );
                    Toast.makeText(LbsGeocodingActivity.this, message, Toast.LENGTH_LONG).show();
                }

                public void onStatusChanged(String s, int i, Bundle b) {
                    Toast.makeText(LbsGeocodingActivity.this, "Provider status changed",
                            Toast.LENGTH_LONG).show();
                }

                public void onProviderDisabled(String s) {
                    Toast.makeText(LbsGeocodingActivity.this,
                            "Provider disabled by the user. GPS turned off",
                            Toast.LENGTH_LONG).show();

                }

                public void onProviderEnabled(String s) {
                    Toast.makeText(LbsGeocodingActivity.this,
                            "Provider enabled by the user. GPS turned on",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}


