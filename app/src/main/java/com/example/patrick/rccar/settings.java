package com.example.patrick.rccar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class settings extends AppCompatActivity {
    private static final String TAG = "Settings";
    private Button blue;
    private ToggleButton USONOFF;
    Recording recording = new Recording();



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        blue = (Button) findViewById(R.id.buttonbluetooth);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBluetooth();
            }
        });



        USONOFF = (ToggleButton) findViewById(R.id.USONOFF);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean tgstate = preferences.getBoolean("tgstate", true);  //default is true
        if (tgstate) //if (tgpref) may be enough, not sure
        {
            USONOFF.setChecked(true);
        }
        else
        {
            USONOFF.setChecked(false);
        }


        USONOFF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    recording.sendMessageToBT("USON");
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgstate", true); // value to store
                    editor.commit();

                }
                else {
                    recording.sendMessageToBT("USOFF");
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgstate", false); // value to store
                    editor.commit();
                }
            }
        });



    }

    public void enableDisableUS() {

        /*if (USONOFF.isChecked()) {
            recording.sendMessageToBT("USON");
            USONOFF.setChecked(true);
        } else {
            recording.sendMessageToBT("USOFF");
            USONOFF.setChecked(false);
        }*/

    }

    public void openBluetooth() {
        Intent intent = new Intent(this, bluetooth.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in3, R.anim.fade_out);

    }
}
