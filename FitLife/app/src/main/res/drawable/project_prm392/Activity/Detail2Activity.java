package com.example.project_prm392.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392.Model.Exercise;
import com.example.project_prm392.R;
import com.example.project_prm392.Service.SQLiteHelper;

public class Detail2Activity extends AppCompatActivity {
    private TextView nameTextView, repeatTextView, durationTextView, timerText;
    private ImageView exerciseImageView;
    private SQLiteHelper databaseHelper;
    private ProgressBar progressBar;
    private Button skipButton;
    private int progressStatus = 60;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private Handler handler = new Handler();
    private Thread timerThread;

    private void bindingView(){
        nameTextView = findViewById(R.id.nameTextView);
        repeatTextView = findViewById(R.id.repeatTextView);
        progressBar = findViewById(R.id.timer);
        timerText = findViewById(R.id.counting);
        skipButton = findViewById(R.id.skip);
        exerciseImageView = findViewById(R.id.exerciseImageView);
    }
    private void bindingAction(){
        skipButton.setOnClickListener(this::skipButtonOnClick);
    }

    private void skipButtonOnClick(View view) {
        if (isRunning && !isPaused) {
            isPaused = true;
            skipButton.setText("RESUME");
        } else if (isRunning) {
            isPaused = false;
            skipButton.setText("SKIP");
            startTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail2);

        bindingView();
        bindingAction();


        databaseHelper = new SQLiteHelper(this);
        int exerciseId = getIntent().getIntExtra("exerciseId", -1);
        if (exerciseId != -1) {
            Exercise exercise = databaseHelper.getExerciseById(exerciseId);
            if (exercise != null) {
                nameTextView.setText(exercise.getName());
                repeatTextView.setText("Repeat: " + exercise.getRepeatTimes() + " times");
                exerciseImageView.setImageResource(exercise.getImageResId());

            }
        }

        progressBar.setMax(60);
        progressBar.setProgress(progressStatus);
        timerText.setText(progressStatus + "");

//        skipButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isRunning && !isPaused) {
//                    isPaused = true;
//                    skipButton.setText("RESUME");
//                } else if (isRunning) {
//                    isPaused = false;
//                    skipButton.setText("SKIP");
//                    startTimer();
//                }
//            }
//        });
    }

    public void btnPlay(View view) {
        if (!isRunning) {
            isRunning = true;
            startTimer();
        }

    }

    private void startTimer() {
        if (timerThread != null && timerThread.isAlive()) {
            return;
        }

        timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus > 0 && isRunning) {
                    if (!isPaused) {
                        progressStatus--;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                timerText.setText(progressStatus + "");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Reset lại khi timer kết thúc
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        isRunning = false;
                        isPaused = false;
                        skipButton.setText("SKIP");
                        progressStatus = 60;
                    }
                });
            }
        });
        timerThread.start();
    }
}