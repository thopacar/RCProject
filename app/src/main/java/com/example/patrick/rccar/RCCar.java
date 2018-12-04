package com.example.patrick.rccar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RCCar extends AppCompatActivity {

private ImageButton button;
private Button live;
private Button rec;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ToolBar Transparent
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

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
