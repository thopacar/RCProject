package com.example.patrick.rccar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.patrick.rccar.listener.BeschleunigungsListener;
import com.example.patrick.rccar.listener.BremsListener;
import com.example.patrick.rccar.listener.LenkungsListener;

public abstract class Steuerung extends AppCompatActivity {
    public SeekBar sb1;
    public TextView tvsb1;
    // ------- SEEKBAR -------
    int sbvalue;
    int sbmax = 100;
    int sbstart = 0;
    private TextView yText;
    private Sensor mySensor;
    private SensorManager SM;
    private ImageButton btn_bremse;
    private LenkungsListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        btn_bremse = (ImageButton)findViewById(R.id.bremse);
        btn_bremse.setOnTouchListener(new BremsListener(this));


// ----------------- ACCELORMETER ------------------------
        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelormeter Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        listener = new LenkungsListener(this);


        // Assign TextView
        yText = (TextView) findViewById(R.id.yText);

// ----------------- ACCELORMETER ENDE --------------------

// ------ SEEKBAR ------- https://www.youtube.com/watch?v=kKxhLrLumRk ---------------- http://www.zoftino.com/android-seekbar-and-custom-seekbar-examples

        sb1 = (SeekBar)findViewById(R.id.seekBar1);
        tvsb1 = (TextView) findViewById(R.id.seekBarTextview);

        sb1.setMax(sbmax);
        sb1.setProgress(sbstart);
        tvsb1.setText(Integer.toString(sbstart) + "%");
        sb1.setOnSeekBarChangeListener(new BeschleunigungsListener(this));
// ------ SEEKBAR Ende -----
        onCreate();
    }

    protected abstract void onCreate();

    protected abstract void setContentView();

    public void sendMessageToBT(Object msg) {
        new Thread(new WorkerThread(this, msg)).start();
    }

    public void setBeschleunigungsText(int progress) {
        tvsb1.setText(progress + "%");
    }

    public void setLenkText(float value) {
        yText.setText("Y: " + value);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SM.unregisterListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SM.registerListener(listener, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
