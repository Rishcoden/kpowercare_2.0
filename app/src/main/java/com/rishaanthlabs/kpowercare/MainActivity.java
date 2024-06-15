package com.rishaanthlabs.kpowercare;

import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView batteryPercentageTextView;
    private TextView reportTextView; // New TextView for battery status
    private boolean isAnimationStarted = false;

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            if (level != -1 && scale != -1) {
                int batteryPercentage = (int) ((level / (float) scale) * 100);

                if (!isAnimationStarted) {
                    startBatteryAnimation(batteryPercentage);
                    isAnimationStarted = true;
                } else {
                    setBatteryStatusText(batteryPercentage);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button optimizer = findViewById(R.id.optimize);
        optimizer.setBackground(ContextCompat.getDrawable(this,R.drawable.optimizer));
        optimizer.setBackgroundTintMode(null);
        optimizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,optimze_operarion.class);
                startActivity(intent);
            }
        });

       //battery health


        TextView t = findViewById(R.id.t);

// Get the installation time in milliseconds
        long firstBootTime = System.currentTimeMillis() - System.currentTimeMillis() + Build.TIME;

// Convert firstBootTime to LocalDate
        LocalDate installationDate = new Date(firstBootTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

// Calculate the number of months between the installation date and the current date
        long monthsBetween = ChronoUnit.MONTHS.between(installationDate, currentDate);

// Display the result in the TextView
        if(monthsBetween<=6){
            t.setText("100% ");} else if (monthsBetween<=12) {
            t.setText("96%");
        } else if (monthsBetween<=14) {
            t.setText("92% ");}else if (monthsBetween<=18) {
            t.setText("88% ");}else if (monthsBetween<=21) {
            t.setText("85% ");}else if (monthsBetween<=24) {
            t.setText("80% ");}else if (monthsBetween<=30) {
            t.setText("76% ");}else if (monthsBetween<=36) {
            t.setText("70% ");}else if (monthsBetween<=40) {
            t.setText("67% ");}else if (monthsBetween<=45) {
            t.setText("62% ");}else if (monthsBetween<=50) {
            t.setText("57% ");}else if (monthsBetween<=55) {
            t.setText("53% ");}else if (monthsBetween<=60) {
            t.setText("47% ");}else if (monthsBetween<=65) {
            t.setText("42% ");}else if (monthsBetween<=70) {
            t.setText("40% ");}else if (monthsBetween<=75) {
            t.setText(" Battery is already Dimnished ");}


        // Initialize the TextViews
        batteryPercentageTextView = findViewById(R.id.batteryPercentageTextView);
        reportTextView = findViewById(R.id.report); // Find the report TextView

        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    private void startBatteryAnimation(int actualBatteryPercentage) {
        ValueAnimator animator = ValueAnimator.ofInt(100, actualBatteryPercentage);
        animator.setDuration(5000); // 5 seconds
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                batteryPercentageTextView.setText(animatedValue + "%");
                setBatteryStatusText(animatedValue); // Update the status text
            }
        });
        animator.start();
    }

    private void setBatteryStatusText(int batteryPercentage) {
        if (batteryPercentage >= 90) {
            reportTextView.setText("Excellent");
        } else if (batteryPercentage >= 50) {
            reportTextView.setText("Optimum State");
        } else if (batteryPercentage >= 30) {
            reportTextView.setText("Optimization Required");
        } else {
            reportTextView.setText("Critical State");
        }
    }
}
