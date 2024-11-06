package com.example.project_prm392.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392.Adapter.ChatAdapter;
import com.example.project_prm392.Model.MessageModel;
import com.example.project_prm392.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GPTActivity extends AppCompatActivity {

    private static final String TAG = "GPTActivity";
    private RecyclerView rcy_chat;
    private EditText edtTextChat;
    private Button btnSendChat;
    private static ArrayList<MessageModel> messageList;
    private ChatAdapter chatAdapter;
    private Map<String, String> gymQnA;
    private BottomNavigationView bottomNavigationView;

    private void bindingView() {
        rcy_chat = findViewById(R.id.rcy_chat);
        edtTextChat = findViewById(R.id.edtTextChat);
        btnSendChat = findViewById(R.id.btnSendChat);
        bottomNavigationView = findViewById(R.id.bottom_navigation3);
    }

    private void bindingAction() {
        btnSendChat.setOnClickListener(this::onClickBtnSendChat);
    }

    private void onClickBtnSendChat(View view) {
        String text = edtTextChat.getText().toString();
        if (!text.isEmpty()) {
            messageList.add(new MessageModel(text, false));
            chatAdapter.notifyItemInserted(messageList.size() - 1);
            rcy_chat.scrollToPosition(messageList.size() - 1);
            respondToUserInput(text);
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
        edtTextChat.setText("");
    }
    private void respondToUserInput(String input) {
        String response = gymQnA.getOrDefault(input, "disconnect the gym master model");

        runOnUiThread(() -> {
            messageList.add(new MessageModel(response, true));
            chatAdapter.notifyItemInserted(messageList.size() - 1);
            rcy_chat.scrollToPosition(messageList.size() - 1);
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gptactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
        loadData();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();
                if (id == R.id.home) {
                    intent = new Intent(GPTActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (id == R.id.bmi) {
                    intent = new Intent(GPTActivity.this, BMIActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }else if (id == R.id.chat_gpt) {
                    intent = new Intent(GPTActivity.this, GPTActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    private void loadData() {
        messageList = new ArrayList<>();
        messageList.add(new MessageModel("Hello. How can I help you?", false));
        setUpRecyclerView();

        // Khởi tạo danh sách câu hỏi và câu trả lời về gym
        gymQnA = new HashMap<>();
        gymQnA.put("What are the benefits of going to the gym?", "The benefits include improved physical health, mental well-being, and social interaction.");
        gymQnA.put("How often should I go to the gym?", "It's recommended to go at least 3-4 times a week for optimal results.");
        gymQnA.put("What exercises should I start with?", "Start with basic compound movements like squats, deadlifts, and bench presses.");
        gymQnA.put("How do I lose weight at the gym?", "Combine cardiovascular exercises with strength training and maintain a calorie deficit.");
        gymQnA.put("What should I eat before a workout?", "Consume a balanced meal with protein, carbs, and fats about 1-2 hours before your workout.");
        gymQnA.put("What should I eat after a workout?", "Post-workout meals should include protein and carbohydrates to aid recovery and muscle growth.");
        gymQnA.put("How long should my workouts be?", "Workouts should typically last between 45 minutes to an hour, depending on your goals.");
        gymQnA.put("What is the best time to go to the gym?", "The best time depends on your schedule, but many find morning or late afternoon workouts effective.");
        gymQnA.put("Should I do cardio or weights first?", "It depends on your goals, but generally, it's recommended to do weights first to maximize strength gains.");
        gymQnA.put("How do I build muscle?", "Focus on progressive overload, proper nutrition, and sufficient rest.");
        gymQnA.put("What supplements should I take?", "Common supplements include protein powder, creatine, and BCAAs, but consult with a nutritionist or doctor first.");
        gymQnA.put("How do I stay motivated to go to the gym?", "Set realistic goals, track your progress, and find a workout buddy or community.");
        gymQnA.put("What is a good gym routine for beginners?", "A good routine includes a mix of cardio and strength training exercises 3-4 times a week.");
        gymQnA.put("How do I prevent injuries at the gym?", "Warm-up properly, use correct form, and avoid lifting too heavy weights too soon.");
        gymQnA.put("Can I gain muscle without lifting heavy weights?", "Yes, using lighter weights with higher repetitions and proper form can also build muscle.");
        gymQnA.put("How important is stretching?", "Stretching is crucial for flexibility, injury prevention, and muscle recovery.");
        gymQnA.put("What are some common gym mistakes?", "Common mistakes include improper form, not warming up, and not having a balanced routine.");
        gymQnA.put("How much water should I drink while working out?", "Drink about 500-600 ml of water 2-3 hours before working out and 200-300 ml during your workout.");
        gymQnA.put("What is HIIT?", "HIIT stands for High-Intensity Interval Training, which involves short bursts of intense exercise followed by rest periods.");
        gymQnA.put("Can I workout every day?", "It's possible, but it's important to vary the intensity and type of workouts to avoid overtraining and injury.");
    }
    private void setUpRecyclerView() {
        chatAdapter = new ChatAdapter(messageList, this);
        rcy_chat.setLayoutManager(new LinearLayoutManager(this));
        rcy_chat.setAdapter(chatAdapter);
    }
}

/*
package com.example.project_prm392.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392.Adapter.ChatAdapter;
import com.example.project_prm392.Model.MessageModel;
import com.example.project_prm392.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GPTActivity extends AppCompatActivity {
    private static final String TAG = "GPTActivity";
    private RecyclerView rcy_chat;
    private EditText edtTextChat;
    private Button btnSendChat;
    private static ArrayList<MessageModel> messageList;
    private ChatAdapter chatAdapter;
    private OkHttpClient client;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "";

    private void bindingView() {
        rcy_chat = findViewById(R.id.rcy_chat);
        edtTextChat = findViewById(R.id.edtTextChat);
        btnSendChat = findViewById(R.id.btnSendChat);
    }

    private void bindingAction() {
        btnSendChat.setOnClickListener(this::onClickBtnSendChat);
    }

    private void onClickBtnSendChat(View view) {
        String text = edtTextChat.getText().toString();
        if (!text.isEmpty()) {
            messageList.add(new MessageModel(text, false));
            chatAdapter.notifyItemInserted(messageList.size() - 1);
            rcy_chat.scrollToPosition(messageList.size() - 1);
            sendMessageToServer(text);
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
        edtTextChat.setText("");
    }

    private void sendMessageToServer(String message) {
        client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "gpt-3.5-turbo");
            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", message);
            messages.put(userMessage);
            jsonObject.put("messages", messages);
        } catch (JSONException e) {
            Log.e(TAG, "JSON Exception: " + e.getMessage());
        }

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "HTTP Request Failed: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(GPTActivity.this, "Server Error", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        Log.d(TAG, "Response: " + responseBody);
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONArray choicesArray = jsonResponse.getJSONArray("choices");
                        if (choicesArray.length() > 0) {
                            JSONObject messageObject = choicesArray.getJSONObject(0).getJSONObject("message");
                            String reply = messageObject.getString("content");

                            runOnUiThread(() -> {
                                messageList.add(new MessageModel(reply, true));
                                chatAdapter.notifyItemInserted(messageList.size() - 1);
                                rcy_chat.scrollToPosition(messageList.size() - 1);
                            });
                        } else {
                            Log.e(TAG, "No choices in response");
                            runOnUiThread(() -> Toast.makeText(GPTActivity.this, "No response from API", Toast.LENGTH_SHORT).show());
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "JSON Parsing Error: " + e.getMessage());
                    }
                } else {
                    Log.e(TAG, "API Response Error: " + response.message());
                    Log.e(TAG, "Response Code: " + response.code());
                    String responseBody = response.body().string();
                    Log.e(TAG, "Response Body: " + responseBody);
                    runOnUiThread(() -> Toast.makeText(GPTActivity.this, "API Error: " + response.message(), Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gptactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
        loadData();
    }

    private void loadData() {
        messageList = new ArrayList<>();
        messageList.add(new MessageModel("Hello. How can I help you?", false));
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        chatAdapter = new ChatAdapter(messageList, this);
        rcy_chat.setLayoutManager(new LinearLayoutManager(this));
        rcy_chat.setAdapter(chatAdapter);
    }
}

 */