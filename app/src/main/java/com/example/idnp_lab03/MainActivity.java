package com.example.idnp_lab03;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private BatteryBroadcastReceiver batteryReceiver;
    private TextView batteryStatusText;
    private TextView batteryLevelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryStatusText = findViewById(R.id.battery_status_text);
        batteryLevelText = findViewById(R.id.battery_level_text);

        batteryReceiver = new BatteryBroadcastReceiver(batteryStatusText, batteryLevelText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(Intent.ACTION_BATTERY_CHANGED), 0);

        registerReceiver(batteryReceiver, filter);
        Log.i("BatteryMonitor", "BroadcastReceiver registrado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryReceiver);
        Log.i("BatteryMonitor", "BroadcastReceiver desregistrado");
    }
}