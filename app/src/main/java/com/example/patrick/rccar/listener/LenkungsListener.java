package com.example.patrick.rccar.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;

import com.example.patrick.rccar.Steuerung;

public class LenkungsListener implements SensorEventListener {
    private final Steuerung steuerung;

    public LenkungsListener(Steuerung steuerung) {
        this.steuerung = steuerung;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        steuerung.setLenkText(event.values[1]);
        steuerung.sendMessageToBT(String.format("y%d", convertToHundretBaseInt(event.values[1])));
    }
    private Integer convertToHundretBaseInt(float value) {
        return Float.valueOf(value*10).intValue();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
