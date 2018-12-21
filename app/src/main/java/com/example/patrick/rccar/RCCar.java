package com.example.patrick.rccar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class RCCar extends AppCompatActivity {

   // UUID MY_UUID = UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee");
   // BluetoothDevice mmDevice = null;
     BluetoothSocket mmSocket = null;
     OutputStream mmOutputStream;
    //BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
   // Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

   // private static final String TAG = "RCCar";

private ImageButton button;
private Button live;
private Button rec;
private ImageButton btn_info;
private Button btn_connect;

    bluetoothconnection bt = bluetoothconnection.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ToolBar Transparent
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

        button =(ImageButton) findViewById(R.id.imageButtonSetting);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openSettings();
            }
        });

        live =(Button) findViewById(R.id.livebutton);
        live.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openLive();
            }
        });

        rec =(Button) findViewById(R.id.recbutton);
        rec.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openRec();
            }
        });

        btn_info = (ImageButton) findViewById(R.id.info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new Thread(new WorkerThread("starteskript"))).start();
                Intent i = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in3, R.anim.fade_out);
            }
            });

        btn_connect = (Button) findViewById(R.id.connect);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                bt.onClick();
            }
        });



    }
    public void sendBtMsg(String msg2send) throws IOException {

            mmSocket = bt.mmSocket;
            String msg = msg2send;

            mmOutputStream = mmSocket.getOutputStream();
            mmOutputStream.write(msg.getBytes());

        }
    public void openSettings() {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in3, R.anim.fade_out);

    }
    public void openLive() {
        Intent intent = new Intent(this, live.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in3, R.anim.fade_out);

    }
    public void openRec() {
        Intent intent = new Intent(this, recording.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in3, R.anim.fade_out);

    }
}
