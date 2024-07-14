package com.example.project182.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project182.Database.DatabaseHelper;
import com.example.project182.Entity.Account;
import com.example.project182.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button loginBtn;
    private Button goToRegisterBtn;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        goToRegisterBtn = findViewById(R.id.go_to_register);

        goToRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                boolean isFull = true;

                if (TextUtils.isEmpty(userName.getText())) {
                    Toast.makeText(getApplicationContext(), "User name need to fill", Toast.LENGTH_SHORT).show();
                    isFull = false;
                }

                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getApplicationContext(), "Password need to fill", Toast.LENGTH_SHORT).show();
                    isFull = false;
                }

                if (isFull) {
                    Account account = dbHelper.getAccountByUsername(userName.getText().toString());

                    if (account != null && Objects.equals(account.getPassword(), password.getText().toString())) {
                        intent.putExtra("userId", account.getId());
                        startActivity(intent);
                    } else {
                        // Login failed
                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                        userName.setText("");
                        password.setText("");
                    }
                }
            }
        });


        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}