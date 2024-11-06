package com.example.fitlife.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitlife.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    private android.widget.Button mcalculaytebmi;

    private TextView mcurrentheight, mcurrentage, mcurrentweight;
    private ImageView mincrementage, mincrementweight, mdecrementweight,mdecrementage;
    private SeekBar mseekbarforheight;
    RelativeLayout mmale, mfemale;
    private double intweight = 55.0;
    private int intage = 22;
    private int currentprogress;
    private String mintprogress = "170";
    private String typeofuser = "0";
    private String weight = "55.0";
    private String age2 = "22";
    private DecimalFormat decimalFormat;

    private BottomNavigationView bottomNavigationView;

    private void bindingView(){
        mcalculaytebmi = findViewById(R.id.calculatebmi);
        bottomNavigationView = findViewById(R.id.bottom_navigation2);
        mcurrentage = findViewById(R.id.currentage);
        mcurrentweight= findViewById(R.id.currentweight);
        mcurrentheight= findViewById(R.id.currentheight);
        mincrementage= findViewById(R.id.increementage);
        mdecrementage= findViewById(R.id.decreementage);
        mincrementweight = findViewById(R.id.increementtweight);
        mdecrementweight = findViewById(R.id.decreementtweight);
        mseekbarforheight = findViewById(R.id.seekbarforheight);
        mmale = findViewById(R.id.male);
        mfemale = findViewById(R.id.female);
        decimalFormat = new DecimalFormat("#.#");

    }
    private void bindingAction(){
        mcalculaytebmi.setOnClickListener(this::mcalculateOnclick);
        mmale.setOnClickListener(this:: mmaleOnclick);
        mfemale.setOnClickListener(this::mfemaleOnclick);
        mincrementage.setOnClickListener(this:: mincrementageOnclick);
        mincrementweight.setOnClickListener(this::mincrementweightOnclick);
        mdecrementweight.setOnClickListener(this::mdecrementweightOnclick);
        mdecrementage.setOnClickListener(this::mdecrementageOnclick);
    }

    private void mdecrementageOnclick(View view) {
        intage --;
        age2 = String.valueOf(intage);
        mcurrentage.setText(age2);
    }

    private void mdecrementweightOnclick(View view) {
        intweight -= 0.1;
        weight = decimalFormat.format(intweight);
        mcurrentweight.setText(weight);
    }

    private void mincrementweightOnclick(View view) {
        intweight += 0.1;
        weight = decimalFormat.format(intweight);
        mcurrentweight.setText(weight);

    }

    private void mincrementageOnclick(View view) {
        intage ++;
        age2 = String.valueOf(intage);
        mcurrentage.setText(age2);
    }

    private void mfemaleOnclick(View view) {
        mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalefocus));
        mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalenotfoucus));
        typeofuser="Female";
    }

    private void mmaleOnclick(View view) {
        mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalefocus));
        mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalenotfoucus));
        typeofuser="Male";
    }

    private void mcalculateOnclick(View view) {
        if(typeofuser.trim().equals("0")){
            Toast.makeText(getApplicationContext(), "Select Your Gender", Toast.LENGTH_LONG).show();
        }else if(mintprogress.trim().equals("0")){
            Toast.makeText(getApplicationContext(), "Select Your Height", Toast.LENGTH_LONG).show();
        }else if(intage == 0 || intage < 0){
            Toast.makeText(getApplicationContext(), "Age Is Incorrect", Toast.LENGTH_LONG).show();
        }else if(intweight == 0 || intweight < 0){
            Toast.makeText(getApplicationContext(), "Weight Is Incorrect", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(BMIActivity.this, GoodBMIActivity.class);
            intent.putExtra("gender",typeofuser);
            intent.putExtra("height",mintprogress);
            intent.putExtra("weight",weight);
            intent.putExtra("age",age2);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();

        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress = progress;
                mintprogress = String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();
                if (id == R.id.home) {
                    intent = new Intent(BMIActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id == R.id.bmi) {
                    intent = new Intent(BMIActivity.this, BMIActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }else if (id == R.id.chat_gpt) {
                    intent = new Intent(BMIActivity.this, GPTActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
}