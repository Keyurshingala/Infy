package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PostModel.Post;
import com.example.myapplication.databinding.ItemPostBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Context context;
    List<Post> userList;
    onClickListener onClickListener;
    String btn="No Btn";

    public PostAdapter(List<Post> userList, Context context, onClickListener onClickListener, String btn) {
        this.context = context;
        this.userList = userList;
        this.onClickListener = onClickListener;
        this.btn = btn;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (btn.equals("delete")) {
            holder.binding.btnDeletePost.setVisibility(View.VISIBLE);
        }
        if (btn.equals("in")) {
            holder.binding.btnInsertPost.setVisibility(View.VISIBLE);
        }

        Post post = userList.get(position);

        holder.binding.postId.setText("" + post.getId());
        holder.binding.postTitle.setText(post.getTitle());
        holder.binding.postBody.setText(post.getBody());


        holder.binding.btnDeletePost.setOnClickListener(v -> {
            onClickListener.onCardClick(post);
        });

        holder.binding.btnInsertPost.setOnClickListener(v -> {
            onClickListener.onCardClick(post);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ItemPostBinding binding;

        public MyViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickListener {
        void onCardClick(Post post);
    }
}
