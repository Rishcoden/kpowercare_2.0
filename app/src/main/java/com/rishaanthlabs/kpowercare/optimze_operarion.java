package com.rishaanthlabs.kpowercare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class optimze_operarion extends AppCompatActivity {
    private static final int REQUEST_BLUETOOTH_CONNECT_PERMISSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimze_operarion);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        CheckBox brightness = findViewById(R.id.brightness);
        CheckBox timeout = findViewById(R.id.timeout);
        CheckBox blue = findViewById(R.id.blue);
        CheckBox sync = findViewById(R.id.sync);
        CheckBox gps = findViewById(R.id.gps);
        Button bt = findViewById(R.id.touch_optmize);
        Button click = findViewById(R.id.clickme);
        bt.setBackground(ContextCompat.getDrawable(this,R.drawable.optimizer));
        bt.setBackgroundTintMode(null);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(optimze_operarion.this, additional_opt.class);
                startActivity(intent);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(optimze_operarion.this,optizme_animation.class);
                startActivity(intent);
                if (brightness.isChecked()){
                    int brightnessLevel = (int) (255 * 0.25); // 25% of maximum brightness
                    Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightnessLevel);
                    // Apply the new brightness level
                    ContentResolver resolver = getContentResolver();
                    Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                    Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightnessLevel);

                }
                if (blue.isChecked()){
                    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                    if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                    }


                }
                if(gps.isChecked()) {


                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        try {
                            // Requires ACCESS_MOCK_LOCATION permission, which is only granted to system apps
                            Settings.Secure.putInt(getContentResolver(), Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (sync.isChecked()){
                    ContentResolver.setMasterSyncAutomatically(false);
                }
                if (timeout.isChecked()){

                    int timeoutMillis = 15000; // 15 seconds in milliseconds
                    Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, timeoutMillis);

                }
            }
        });






    }
}