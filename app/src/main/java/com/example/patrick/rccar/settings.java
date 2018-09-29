package com.example.patrick.rccar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class settings extends AppCompatActivity {

    private Button blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        blue =(Button) findViewById(R.id.buttonbluetooth);
        blue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openBluetooth();
            }
        });

    }

    public void openBluetooth() {
        Intent intent = new Intent(this, bluetooth.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in3, R.anim.fade_out);

    }
}
