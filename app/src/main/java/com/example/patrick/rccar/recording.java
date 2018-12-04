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


public class recording extends AppCompatActivity implements SensorEventListener{

    private TextView yText;
    private Sensor mySensor;
    private SensorManager SM;

    public SeekBar sb1;
    public TextView tvsb1;
    // ------- SEEKBAR -------
    int sbvalue;
    int sbmax = 100;
    int sbstart = 0;
    // -----------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
// ----------------- ACCELORMETER ------------------------
        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelormeter Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Assign TextView
        yText = (TextView) findViewById(R.id.yText);

// ----------------- ACCELORMETER ENDE --------------------

// ------ SEEKBAR ------- https://www.youtube.com/watch?v=kKxhLrLumRk ---------------- http://www.zoftino.com/android-seekbar-and-custom-seekbar-examples

        sb1 = (SeekBar)findViewById(R.id.seekBar1);
        tvsb1 = (TextView) findViewById(R.id.seekBarTextview);

        sb1.setMax(sbmax);
        sb1.setProgress(sbstart);
        tvsb1.setText(Integer.toString(sbstart) + "%");
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tvsb1.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                sb1.setProgress(0);
            }
        });
// ------ SEEKBAR Ende -----
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        yText.setText("Y: " + event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        // wird nicht verwendet
    }
}

