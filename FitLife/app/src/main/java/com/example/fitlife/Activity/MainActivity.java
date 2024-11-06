package com.example.fitlife.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitlife.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private Button btnstart, btnstart2,btnstart3;
    private BottomNavigationView bottomNavigationView;

    private void bindingView(){
        btnstart = findViewById(R.id.btnStart);
        btnstart2 = findViewById(R.id.btnStart2);
        btnstart3 = findViewById(R.id.btnStart3);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        Toolbar toolbar = findViewById(R.id.toorbar);
        setSupportActionBar(toolbar);
    }

    private void bindingAction(){
        btnstart.setOnClickListener(this::onBtnStartClick);
        btnstart2.setOnClickListener(this::onBtnStart2Click);
        btnstart3.setOnClickListener(this::onBtnStart3Click);
        bottomNavigationView.setOnItemSelectedListener(this::btnNavigationViewSelected);
    }

    private boolean btnNavigationViewSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        if (id == R.id.home) {
            intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.bmi) {
            intent = new Intent(MainActivity.this, BMIActivity.class);
            startActivity(intent);
            finish();
            return true;
        }else if (id == R.id.chat_gpt) {
            intent = new Intent(MainActivity.this, GPTActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onBtnStart3Click(View view) {
        Intent intent = new Intent(this, ExerciseScreen3.class );
        startActivity(intent);
    }

    private void onBtnStart2Click(View view) {
        Intent intent = new Intent(this, ExerciseScreen2.class );
        startActivity(intent);
    }

    private void onBtnStartClick(View view) {
        Intent intent = new Intent(this, ExerciseScreen.class );
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
    }

    public void morning(View view) {
        Intent intent = new Intent(this, ExerciseScreen.class );
        startActivity(intent);
    }

    public void afternoon(View view) {
        Intent intent = new Intent(this, ExerciseScreen2.class );
        startActivity(intent);
    }
    public void advanced(View view) {
        Intent intent = new Intent(this, ExerciseScreen3.class );
        startActivity(intent);
    }
}
