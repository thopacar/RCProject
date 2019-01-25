package com.example.patrick.rccar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
//Accelormeter https://www.youtube.com/watch?v=YrI2pCZC8cc
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ToggleButton;

import com.example.patrick.rccar.listener.BeschleunigungsListener;
import com.example.patrick.rccar.listener.BremsListener;
import com.example.patrick.rccar.listener.RecListener;


public class Recording extends Steuerung {

    private ToggleButton btn_rec;

    @Override
    protected void onCreate() {
        btn_rec = (ToggleButton) findViewById(R.id.rec);
        btn_rec.setOnClickListener(new RecListener(this));
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_recording);
    }


}

