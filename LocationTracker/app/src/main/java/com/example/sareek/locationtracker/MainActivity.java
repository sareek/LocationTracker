package com.example.sareek.locationtracker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnShowLocation;
    Geocoder geocoder;
    List<Address> addresses;

    // GPSTracker class
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    try {
                        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        String address = addresses.get(0).getAddressLine(0);
                        String city = addresses.get(0).getLocality();
                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nCity: " + city + "\naddress: " + address, Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        System.out.printf("error occured");
                        e.printStackTrace();
                    }
                        }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });

    }
}
   /*
   //USE THIS SECTION IF NEW ACTIVITY IS REQUIRED NAMED Rate.java ELSE IGNORE OR DELETE THIS
   public void Rate(View view) {
    */

  //      Intent myIntent = new Intent(MainActivity.this, Rate.class);
        //myIntent.putExtra("key", value); //Optional parameters
      //  MainActivity.this.startActivity(myIntent);
//}