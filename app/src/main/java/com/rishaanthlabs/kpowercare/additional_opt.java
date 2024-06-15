package com.rishaanthlabs.kpowercare;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class additional_opt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_opt);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Button wlan =findViewById(R.id.wifite);
        Button blue2 = findViewById(R.id.bluet);
        Button dark = findViewById(R.id.dark);
        Button defualt = findViewById(R.id.systembattery);
        wlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                startActivity(panelIntent);
            }
        });
     dark.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
             startActivity(intent);
         }
     });

        blue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
            }
        });
        defualt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
                startActivity(intent);
            }
        });

    }
}