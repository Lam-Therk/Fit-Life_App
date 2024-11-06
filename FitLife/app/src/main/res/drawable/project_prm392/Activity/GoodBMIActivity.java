package com.example.project_prm392.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_prm392.R;

public class GoodBMIActivity extends AppCompatActivity {

    private android.widget.Button mrecalculatebmi;
    private TextView mbmidisplay, mbmicateogory, mgender;
    private Intent intent;
    private ImageView mimageview;
    private String mbmi, height, weight;
    private float intbmi, intheight, intweight;
    private RelativeLayout mbackground;



    private void bindingView(){
        mrecalculatebmi = findViewById(R.id.recalculatebmi);
        mbmidisplay = findViewById(R.id.bmidisplay);
        mbmicateogory = findViewById(R.id.mbmicateogory);
        mgender = findViewById(R.id.genderdisplay);
        mbackground = findViewById(R.id.contentlayout);
        mimageview = findViewById(R.id.imageview2);
    }
    private void bindingAction(){
        mrecalculatebmi.setOnClickListener(this::mrecalculatebmiOnClick);
    }

    private void mrecalculatebmiOnClick(View view) {
        Intent intent = new Intent(this,BMIActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_good_bmiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        intent = getIntent();

        bindingView();
        bindingAction();

        height = intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");
        intheight =Float.parseFloat(height);
        intweight=Float.parseFloat(weight);
        intheight = intheight/100;
        intbmi = intweight/(intheight*intheight);
        mbmi = Float.toString(intbmi);
        if(intbmi < 16){
            mbmicateogory.setText("Severe Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss1);
        }else if(intbmi < 16.9 && intbmi > 16){
            mbmicateogory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning1);
        }else if(intbmi < 18.4 && intbmi > 17){
            mbmicateogory.setText("Mild Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning1);
        }else if(intbmi < 25 && intbmi > 18.4){
            mbmicateogory.setText("Normal");
            mimageview.setImageResource(R.drawable.goodbmi);
        }else if(intbmi < 29.4 && intbmi > 25){
            mbmicateogory.setText("Over weight");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning1);
        }else{
            mbmicateogory.setText("Obese Class I");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning1);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);

    }
}