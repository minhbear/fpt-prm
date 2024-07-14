package com.example.project182.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project182.Database.DatabaseHelper;
import com.example.project182.Entity.Account;
import com.example.project182.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class ProfileActivity extends AppCompatActivity {
    private Button logoutButton;
    private ImageView backImg;
    private TextView userName;
    private TextView email;
    private ChipNavigationBar chipNavigationBar;
    private DatabaseHelper dbHelper;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dbHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -1);
        account = dbHelper.getAccountById(userId);

        chipNavigationBar = findViewById(R.id.profile_chip_navigation);
        logoutButton = findViewById(R.id.logout_button);
        backImg = findViewById(R.id.profile_backImg);
        userName = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);

        userName.setText(account.getUsername());
        email.setText(account.getUsername());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, IntroActivity.class));
            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                if (id == R.id.explorer) {
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                }
            }
        });
    }
}