package com.rishaanthlabs.kpowercare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class optizme_animation extends AppCompatActivity {
    private LottieAnimationView loaderAnimationView;
    private LottieAnimationView optimizerAnimationView;
    private LottieAnimationView finishAnimationView;
    private TextView statusTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optizme_animation);
        loaderAnimationView = findViewById(R.id.loader);
        optimizerAnimationView = findViewById(R.id.opt_load);
        finishAnimationView = findViewById(R.id.tick);
        statusTextView = findViewById(R.id.statusText);

        // Initially hide the optimizer and finish animation views
        optimizerAnimationView.setVisibility(View.INVISIBLE);
        finishAnimationView.setVisibility(View.INVISIBLE);

        // Handler to start the optimizer animation after 5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loaderAnimationView.cancelAnimation();
                loaderAnimationView.setVisibility(View.GONE);


                statusTextView.setText("Optimizing");


                optimizerAnimationView.setVisibility(View.VISIBLE);
                optimizerAnimationView.playAnimation();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop the optimizer animation
                        optimizerAnimationView.cancelAnimation();
                        optimizerAnimationView.setVisibility(View.GONE);


                        statusTextView.setText("Optimized");


                        finishAnimationView.setVisibility(View.VISIBLE);
                        finishAnimationView.playAnimation();
                    }
                }, 2000);

            }
        }, 5000);
    }
}