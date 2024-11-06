package com.example.fitlife.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitlife.Model.Exercise;
import com.example.fitlife.R;
import com.example.fitlife.Service.SQLiteHelper;

public class DetailActivity extends AppCompatActivity {
    private TextView nameTextView, repeatTextView, durationTextView, timerText;
    private ImageView exerciseImageView;
    private SQLiteHelper databaseHelper;
    private ProgressBar progressBar;
    private Button skipButton;
    private int progressStatus = 5;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private Handler handler = new Handler();
    private Thread timerThread;
    private int count = 2;

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
            skipButton.setText("CONTINUE");
        } else if (isRunning) {
            isPaused = false;
            skipButton.setText("RESUME");
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
                count = exercise.getRepeatTimes();
                repeatTextView.setText("Repeat: " + exercise.getRepeatTimes() + " times");
                exerciseImageView.setImageResource(exercise.getImageResId());

            }
        }

        skipButton.setText("RESUME");
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
            repeatTextView.setText("Repeat: " + count + " times");
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
                        skipButton.setText("NEXT STEP");
                        progressStatus = 5;
                        showCompletionDialog();
                    }
                });
            }
        });
        timerThread.start();
    }
    private void showCompletionDialog() {
        count--;
        if (count > 0) {
            repeatTextView.setText("Repeat: " + count + " times");
            new AlertDialog.Builder(this)
                    .setTitle("Congratulations !")
                    .setMessage("Congratulations on completing the first round, take a break and continue!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Xử lý khi người dùng nhấn OK
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
            skipButton.setText("RESUME");
        }else {
            repeatTextView.setText("Repeat: " + count + " times");
            new AlertDialog.Builder(this)
                    .setTitle("Congratulations !")
                    .setMessage("You have finished the exercise.!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Xử lý khi người dùng nhấn OK
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
            skipButton.setText("RESUME");
            count = 2;
        }

    }
}
