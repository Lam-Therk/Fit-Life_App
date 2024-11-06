package com.example.project_prm392.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_prm392.R;
import com.example.project_prm392.Service.SQLiteHelper;

import java.util.List;

public class ItemActivity extends AppCompatActivity {
    private int[] newArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SQLiteHelper dbHelper = new SQLiteHelper(this);
        List<Integer> exerciseIds = dbHelper.getAllExerciseIds();

        newArray = new int[exerciseIds.size()];
        for (int i = 0; i < exerciseIds.size(); i++) {
            newArray[i] = exerciseIds.get(i);
        }
    }

    public void Imagebtnonclicked(View view) {
        for (int id : newArray) {
            if (view.getId() == id) {
                Intent intent = new Intent(this, Detail2Activity.class);
                startActivity(intent);
                break;
            }
        }
    }
}