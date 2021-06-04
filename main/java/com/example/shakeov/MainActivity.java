package com.example.shakeov;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView myAva;
    private Button btnRot;
    private Button btnOut;
    private Button btnBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAva = (ImageView)findViewById(R.id.ImageView);
        btnRot=(Button)findViewById(R.id.button1);
        btnOut=(Button)findViewById(R.id.button);
        btnBlink=(Button)findViewById(R.id.button2);

        btnRot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation animRot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                        myAva.startAnimation(animRot);
                    }
                });
                btnOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
                        myAva.startAnimation(animFadeOut);
                    }
                });
                 btnBlink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                        myAva.startAnimation(animBlink);
                    }
                });
    }
}