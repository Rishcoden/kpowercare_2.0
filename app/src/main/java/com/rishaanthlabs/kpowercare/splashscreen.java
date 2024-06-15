package com.rishaanthlabs.kpowercare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Thread td = new Thread(){

            public void run(){

                try {
                    sleep(8000);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(splashscreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }



        };td.start();
    }
}