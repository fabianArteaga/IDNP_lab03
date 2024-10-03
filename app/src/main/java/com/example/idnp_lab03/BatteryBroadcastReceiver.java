package com.example.idnp_lab03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.TextView;

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    private TextView batteryStatusText;
    private TextView batteryLevelText;

    public BatteryBroadcastReceiver(TextView statusText, TextView levelText) {
        this.batteryStatusText = statusText;
        this.batteryLevelText = levelText;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale * 100;

        String batteryStatus = (status == BatteryManager.BATTERY_STATUS_CHARGING) ? "Cargando" : "No cargando";

        batteryStatusText.setText("Estado de carga: " + batteryStatus);
        batteryLevelText.setText("Nivel: " + batteryPct + "%");
        Log.i("BatteryStatus", "Estado de carga: " + batteryStatus + ", Nivel: " + batteryPct + "%");
    }
}