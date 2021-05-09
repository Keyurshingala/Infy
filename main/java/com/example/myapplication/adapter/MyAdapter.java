 package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.UserModel.User;
import com.example.myapplication.databinding.ItenUserBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<User> userList;
    onClickListener onClickListener;
    String btn="No Btn";

    public MyAdapter(List<User> userList, Context context, onClickListener onClickListener, String btn) {
        this.context = context;
        this.userList = userList;
        this.onClickListener = onClickListener;
        this.btn = btn;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItenUserBinding binding = ItenUserBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (btn.equals("delete")) {
            holder.binding.btnDeleteUser.setVisibility(View.VISIBLE);
        }
        if (btn.equals("in")) {
            holder.binding.btnInsertUser.setVisibility(View.VISIBLE);
        }

        User user = userList.get(position);

        holder.binding.userName.setText(user.getName());
        holder.binding.userEmail.setText(user.getEmail());
        holder.binding.userCity.setText(user.getAddress().getCity());
        holder.binding.latLang.setText(user.getAddress().getGeo().getLat() + " " + user.getAddress().getGeo().getLng());

        holder.binding.btnDeleteUser.setOnClickListener(v -> {
            onClickListener.onCardClick(user);
        });

        holder.binding.btnInsertUser.setOnClickListener(v -> {
            onClickListener.onCardClick(user);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ItenUserBinding binding;

        public MyViewHolder(@NonNull ItenUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickListener {
        void onCardClick(User user);
    }
}
