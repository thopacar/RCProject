package com.example.patrick.rccar;

import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
//Accelormeter https://www.youtube.com/watch?v=YrI2pCZC8cc
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;


public class recording extends AppCompatActivity implements SensorEventListener{

    private TextView yText;
    private Sensor mySensor;
    private SensorManager SM;
    private ImageButton btn_bremse;
    public SeekBar sb1;
    public TextView tvsb1;
    // ------- SEEKBAR -------
    int sbvalue;
    int sbmax = 100;
    int sbstart = 0;
    // -----------------------

    BluetoothSocket mmSocket = null;
    OutputStream mmOutputStream;
    bluetoothconnection bt = bluetoothconnection.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        final class WorkerThread implements Runnable{

            private String btMsg;
            public WorkerThread(String msg)

            {
                btMsg = msg;
            }

            public void run(){
                try {
                    sendBtMsg(btMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        btn_bremse = (ImageButton)findViewById(R.id.bremse);
        btn_bremse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new Thread(new WorkerThread("bremse"))).start();
            }

        });

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

    public void sendBtMsg(String msg2send) throws IOException {


        mmSocket = bt.mmSocket;

       if(mmSocket == null)
      {
          runOnUiThread(new Runnable() {
              public void run() {
                  Toast.makeText(getApplicationContext(), "Nicht Verbunden", Toast.LENGTH_SHORT).show();
              }
          });

      }
      else {
           String msg = msg2send;

           mmOutputStream = mmSocket.getOutputStream();
           mmOutputStream.write(msg.getBytes());
       }
    }
}

