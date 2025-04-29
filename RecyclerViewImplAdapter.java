package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewImplAdapter extends RecyclerView.Adapter<RecyclerViewImplAdapter.ViewHolder>{
    private ArrayList<UserModel> localDataSet;
    public RecyclerViewImplAdapter(ArrayList<UserModel> dataSet) {
        localDataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView usernameHolder;
        final TextView passwordHolder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameHolder = itemView.findViewById(R.id.username_holder);
            passwordHolder = itemView.findViewById(R.id.password_holder);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.usernameHolder.setText(localDataSet.get(position).username);
        holder.passwordHolder.setText(String.format("Password :%s", localDataSet.get(position).password));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}