package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewImplAdapter recyclerViewAdapter;
    ArrayList<UserModel> recyclerViewData = new ArrayList<UserModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerViewData.addAll(getAllUser());
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewImplAdapter(recyclerViewData);
        recyclerView.setAdapter(recyclerViewAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewData.clear();
        recyclerViewData.addAll(getAllUser());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public ArrayList<UserModel> getAllUser() {
        SqliteHelper dbHelper = new SqliteHelper(this);
        ArrayList<UserModel> users = dbHelper.getAll();
        return users;
    }

    public void addUser(View view) {
        Intent addUser = new Intent(MainActivity.this, AddUserActivity.class);
        startActivity(addUser);
    }
}