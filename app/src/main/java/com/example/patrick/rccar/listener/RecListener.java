package com.example.patrick.rccar.listener;

import android.view.View;
import android.widget.ToggleButton;

import com.example.patrick.rccar.Recording;

public class RecListener implements View.OnClickListener {
    private final Recording recording;

    public RecListener(Recording recording) {

        this.recording = recording;
    }

    @Override
    public void onClick(View v) {
        if (((ToggleButton) v).isChecked()) {
            recording.sendMessageToBT("recOn");
        }
        else{
            recording.sendMessageToBT("recOff");
        }
    }
}
