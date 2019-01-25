package com.example.patrick.rccar.listener;

import android.view.MotionEvent;
import android.view.View;
import com.example.patrick.rccar.Steuerung;

import com.example.patrick.rccar.Recording;

public class BremsListener implements View.OnTouchListener {
    private final Steuerung recording;

    public BremsListener(Steuerung recording) {
        this.recording = recording;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       // if (v.isEnabled()) {
            recording.sendMessageToBT("bremst");
            return false;
        //}
       // else{
       //     recording.sendMessageToBT(0);
       //     return false;
       // }
    }
}
