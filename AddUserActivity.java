package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void saveUser(View view) {
        EditText usernameField = findViewById(R.id.username);
        EditText passwordField = findViewById(R.id.password);
        try (SqliteHelper dbHelper = new SqliteHelper(this)) {
            dbHelper.insert(usernameField.getText().toString(), passwordField.getText().toString());
            Toast.makeText(AddUserActivity.this,"user save successfully", Toast.LENGTH_SHORT).show();
            this.finish();
        } catch (Exception e) {
            Toast.makeText(AddUserActivity.this,e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void cancel(View view) {
        this.finish();
    }
}