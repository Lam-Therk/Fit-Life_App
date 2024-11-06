//package com.example.project_prm392.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.project_prm392.Adapter.ExerciseAdapter;
//import com.example.project_prm392.Model.Exercise;
//import com.example.project_prm392.R;
//import com.example.project_prm392.Service.SQLiteHelper;
//
//import java.util.List;
//
//public class ExerciseScreen extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private ExerciseAdapter adapter;
//    private SQLiteHelper dbHelper;
//    private List<Exercise> exerciseList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_exercise_screen);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        dbHelper = new SQLiteHelper(this);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        exerciseList = dbHelper.getAllExercises();
//        adapter = new ExerciseAdapter(exerciseList, new ExerciseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Exercise exercise) {
//                Intent intent = new Intent(ExerciseScreen.this, Detail2Activity.class);
//                intent.putExtra("exerciseId", exercise.getId());
//                startActivity(intent);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//
//        addInitialData();
//    }
//
//    private void addInitialData() {
//        if (exerciseList.isEmpty()) {
//            dbHelper.addExercise(new Exercise("Mountain Climber", 2, "01:00 MIN", R.drawable.mountain_climbers));
//            dbHelper.addExercise(new Exercise("Bicycle Crunches", 2, "01:00 MIN", R.drawable.bicycle_crunches));
//            dbHelper.addExercise(new Exercise("Butt Bridge", 2, "01:00 MIN", R.drawable.butt_bridge));
//            dbHelper.addExercise(new Exercise("Bent Leg Twist", 2, "01:00 MIN", R.drawable.bent_leg_twist));
//            dbHelper.addExercise(new Exercise("Clapping Crunches", 2, "01:00 MIN", R.drawable.clapping_crunches));
//            dbHelper.addExercise(new Exercise("Cross Arm Crunches", 2, "01:00 MIN", R.drawable.cross_arm_crunches));
//            dbHelper.addExercise(new Exercise("Cross mountain Climber", 2, "01:00 MIN", R.drawable.cross_body_mountain_climber));
//            dbHelper.addExercise(new Exercise("Dead Bug", 2, "01:00 MIN", R.drawable.dead_bug));
//            exerciseList.clear();
//            exerciseList.addAll(dbHelper.getAllExercises());
//            adapter.notifyDataSetChanged();
//        }
//    }
//}
package com.example.project_prm392.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392.Adapter.ExerciseAdapter;
import com.example.project_prm392.Model.Exercise;
import com.example.project_prm392.R;
import com.example.project_prm392.Service.SQLiteHelper;

import java.util.List;

public class ExerciseScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExerciseAdapter adapter;
    private SQLiteHelper dbHelper;
    private List<Exercise> exerciseList;
    private void bindingView(){
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void bindingAction(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new SQLiteHelper(this);
        bindingView();
        bindingAction();
        exerciseList = dbHelper.getExercisesByTypeId(1);
        adapter = new ExerciseAdapter(exerciseList, new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exercise exercise) {
                Intent intent = new Intent(ExerciseScreen.this, Detail2Activity.class);
                intent.putExtra("exerciseId", exercise.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        addInitialData();
    }

    private void addInitialData() {
        if (exerciseList.isEmpty()) {
            dbHelper.addExercise(new Exercise("Mountain Climber", 2, "01:00 MIN", R.drawable.mountain_climbers, 1));
            dbHelper.addExercise(new Exercise("Bicycle Crunches", 2, "01:00 MIN", R.drawable.bicycle_crunches, 1));
            dbHelper.addExercise(new Exercise("Butt Bridge", 2, "01:00 MIN", R.drawable.butt_bridge, 1));
            dbHelper.addExercise(new Exercise("Bent Leg Twist", 2, "01:00 MIN", R.drawable.bent_leg_twist, 1));
            dbHelper.addExercise(new Exercise("Clapping Crunches", 2, "01:00 MIN", R.drawable.clapping_crunches, 1));
            dbHelper.addExercise(new Exercise("Cross Arm Crunches", 2, "01:00 MIN", R.drawable.cross_arm_crunches, 1));
            dbHelper.addExercise(new Exercise("Cross mountain Climber", 2, "01:00 MIN", R.drawable.cross_body_mountain_climber, 1));
            dbHelper.addExercise(new Exercise("Dead Bug", 2, "01:00 MIN", R.drawable.dead_bug, 1));
            exerciseList.clear();
            exerciseList.addAll(dbHelper.getExercisesByTypeId(1));
            adapter.notifyDataSetChanged();
        }
    }
}
