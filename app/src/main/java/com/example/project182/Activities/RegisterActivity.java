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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project182.Database.DatabaseHelper;
import com.example.project182.Entity.Account;
import com.example.project182.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button registerBtn;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        userName = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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

                    if (account == null) {
                        dbHelper.addAccount(new Account(userName.getText().toString(), password.getText().toString()));
                        Toast.makeText(getApplicationContext(), "Register Account Success", Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    } else {
                        // Login failed
                        Toast.makeText(getApplicationContext(), "Account already exit", Toast.LENGTH_SHORT).show();
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