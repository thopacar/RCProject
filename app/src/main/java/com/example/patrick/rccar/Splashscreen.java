package com.example.patrick.rccar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
//Quelle: https://www.youtube.com/watch?v=ZZkanr8tS6w 25.09.2018 15:14 Uhr
public class Splashscreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }
    private void StartAnimations() {


         Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
         anim.reset();
         LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
         l.clearAnimation();
         l.startAnimation(anim);


         anim = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
         anim.reset();
         ImageView iv = (ImageView) findViewById(R.id.splash);
         iv.clearAnimation();
         iv.startAnimation(anim);





        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 6000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            RCCar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in2, R.anim.fade_out);
                    Splashscreen.this.finish();

                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }


}