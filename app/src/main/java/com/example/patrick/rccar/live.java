package com.example.patrick.rccar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
//Accelormeter https://www.youtube.com/watch?v=YrI2pCZC8cc
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Toast;


public class live extends Steuerung {

    @Override
    protected void onCreate() {
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_live);
    }
}
