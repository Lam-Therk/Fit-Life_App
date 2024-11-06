package com.example.project_prm392.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392.R;
import com.example.project_prm392.Service.SQLiteHelper;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword, editTextConfirmPass;
    Button buttonRegister;
    SQLiteHelper dbHelper;

    private void bindingView(){
        editTextUsername = findViewById(R.id.signupname);
        editTextPassword = findViewById(R.id.signuppass);
        editTextConfirmPass = findViewById(R.id.confirmpass);
        buttonRegister = findViewById(R.id.btnsignup);
    }
    private void bindingAction(){
        dbHelper = new SQLiteHelper(this);
        buttonRegister.setOnClickListener(this::onbuttonRegisterClick);

    }

    private void onbuttonRegisterClick(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirm = editTextConfirmPass.getText().toString();

        if (!password.equals(confirm)) {
            Toast.makeText(RegisterActivity.this, "Confirm Password incorrect!!!", Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.addUser(username, password);
            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        bindingView();
        bindingAction();
    }
}