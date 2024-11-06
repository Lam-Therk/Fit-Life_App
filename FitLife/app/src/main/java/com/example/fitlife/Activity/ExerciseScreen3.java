package com.example.fitlife.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlife.Adapter.Exercise2Adapter;
import com.example.fitlife.Model.Exercise;
import com.example.fitlife.R;
import com.example.fitlife.Service.SQLiteHelper;

import java.util.List;

public class ExerciseScreen3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Exercise2Adapter adapter;
    private SQLiteHelper dbHelper;
    private List<Exercise> exerciseList;

    private void bindingView(){
        recyclerView = findViewById(R.id.recyclerView3);
    }
    private void bindingAction(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_screen3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new SQLiteHelper(this);
        bindingView();
        bindingAction();
        exerciseList = dbHelper.getExercisesByTypeId(3);
        adapter = new Exercise2Adapter(exerciseList, new Exercise2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exercise exercise) {
                Intent intent = new Intent(ExerciseScreen3.this, Detail2Activity.class);
                intent.putExtra("exerciseId", exercise.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        addInitialData();
    }

    private void addInitialData() {
        if (exerciseList.isEmpty()) {
            dbHelper.addExercise(new Exercise("Side Leg Raise Left", 2, "01:00 MIN", R.drawable.side_leg_raise_left, 3));
            dbHelper.addExercise(new Exercise("Side Leg Raise Right", 2, "01:00 MIN", R.drawable.leg_drop, 3));
            dbHelper.addExercise(new Exercise("Side Plankhip Left", 2, "01:00 MIN", R.drawable.sideplankhipleft, 3));
            dbHelper.addExercise(new Exercise("Side Plankhip Right", 2, "01:00 MIN", R.drawable.sideplankhipright, 3));
            dbHelper.addExercise(new Exercise("Standing Bicycle", 2, "01:00 MIN", R.drawable.standing_bicycle_crunches, 3));
            dbHelper.addExercise(new Exercise("Swimmer", 2, "01:00 MIN", R.drawable.swimmer_and_superman, 3));
            dbHelper.addExercise(new Exercise("Windshield", 2, "01:00 MIN", R.drawable.roll_up, 3));
            dbHelper.addExercise(new Exercise("Trunk Rotation", 2, "01:00 MIN", R.drawable.trunk_rotation, 2));
            exerciseList.clear();
            exerciseList.addAll(dbHelper.getExercisesByTypeId(3));
            adapter.notifyDataSetChanged();
        }
    }
}
