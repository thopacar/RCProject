package com.example.patrick.rccar.listener;

import android.widget.SeekBar;

import com.example.patrick.rccar.Recording;
import com.example.patrick.rccar.Steuerung;

public class BeschleunigungsListener implements SeekBar.OnSeekBarChangeListener {
    private Steuerung recording;

    public BeschleunigungsListener(Steuerung recording) {
        this.recording = recording;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        recording.setBeschleunigungsText(progress);
        recording.sendMessageToBT(String.format("x%d", Integer.valueOf(progress)));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setProgress(0);
    }
}
