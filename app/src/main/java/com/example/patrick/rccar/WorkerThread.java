package com.example.patrick.rccar;

import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

class WorkerThread implements Runnable {

    private final Steuerung recording;
    private String btMsg;

    public WorkerThread(final Steuerung recording, final Object msg) {
        this.recording = recording;
        btMsg = Objects.toString(msg);
    }

    public void run(){
        sendBtMsg(btMsg);
    }

    public void sendBtMsg(String msg2send) {
        BluetoothSocket mmSocket = bluetoothconnection.getInstance(recording).mmSocket;
        if(mmSocket == null || !mmSocket.isConnected()) {
            recording.runOnUiThread(new Runnable() {
                public void run() {
                    //Toast.makeText(recording.getApplicationContext(), "Nicht Verbunden", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            try {
                mmSocket.getOutputStream().write(msg2send.getBytes());
            } catch (IOException ignore) {
            }
        }
    }
}
