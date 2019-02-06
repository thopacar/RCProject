package com.example.patrick.rccar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class bluetoothconnection extends AppCompatActivity {

    UUID MY_UUID = UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee");
    BluetoothDevice mmDevice = null;
    BluetoothSocket mmSocket = null;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
    private static final String TAG = "bluetoothconnection";

    private static bluetoothconnection instance;
    private static Context mContext;
    private bluetoothconnection() {}

    public static synchronized bluetoothconnection getInstance (Context context) {
        if (bluetoothconnection.instance == null) {
            bluetoothconnection.instance = new bluetoothconnection ();
        }
        mContext = context;
        return bluetoothconnection.instance;
    }

    public void onClick() {
        {
            if(mBluetoothAdapter.isEnabled())
            {
                for (BluetoothDevice device : pairedDevices)
                {
                    if /*(device.getName().equals("PiBluetooth"))*/ ( device.getName().equals("PiBluetooth"))
                        {
                        Log.e("RCProject", device.getName());
                        mmDevice = device;
                        break;
                    }
                }

                //bluetoothconnection bluetoothconnection = new bluetoothconnection();
                //bluetoothconnection.enableDisableconnection();
                try {
                    if (mmSocket == null) {
                        mmSocket = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
                        Log.e(TAG, "enableDisableconnection: Socket wurde erstellt" );
                    }
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (!mmSocket.isConnected()) {

                    Thread connect = new Thread(new Runnable() {
                        public void run() {
                            try {
                                mmSocket.connect();
                                Log.d(TAG, "enableDisableconnection: Connecting.");
                                runOnUiThread(new Runnable()
                                {
                                    public void run()
                                    {
                                        Toast.makeText(mContext, "Erfolgreich Verbunden", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (IOException e) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(mContext, "Keine Verbindung möglich", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Log.e(TAG, "run: Fehler: Verbindung ist fehlgeschlagen",e );
                                e.printStackTrace();
                            }
                        }
                    });
                    connect.start();
                }
                if (mmSocket.isConnected())
                {

                    try {
                        mmSocket.close();
                        mmSocket = null;
                        Log.d(TAG, "enableDisableconnection: Disconnect.");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(mContext, "Verbindung wurde beendet", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (IOException e) {
                        Log.e(TAG, "enableDisableconnection: Fehler: Verbundung konnte nicht beendet werden",e );
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                Toast.makeText(mContext, "Bluetooth ist Deaktiviert", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(), "Bluetooth ist Deaktiviert", Toast.LENGTH_SHORT).show();
            }
        }
       // return mmSocket;
    }
}
