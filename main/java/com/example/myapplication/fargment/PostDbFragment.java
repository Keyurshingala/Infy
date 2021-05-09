package com.example.myapplication.fargment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.PostModel.Post;
import com.example.myapplication.adapter.PostAdapter;
import com.example.myapplication.databinding.FragmentPostDbBinding;
import com.example.myapplication.db.PostDatabase;
import com.example.myapplication.db.Postdb;

import java.util.ArrayList;
import java.util.List;

public class PostDbFragment extends Fragment implements PostAdapter.onClickListener {

    FragmentPostDbBinding binding;
    PostAdapter adapter;
    List<Postdb> postDbList;
    List<Post> postList;
    PostDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPostDbBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = PostDatabase.getPostDatabase(getContext());

        showDb();

    }

    @Override
    public void onCardClick(Post post) {
        //Postdb postdb = new Postdb(post.getId(), post.getTitle(), post.getBody());
        db.postDao().deleteRecord(post.getId());
        postList.remove(post);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();

    }

    private void showDb() {
        postDbList = new ArrayList<>();
        postDbList.addAll(db.postDao().readRecord());

        if (postDbList.size() > 0) {
            postList = new ArrayList<>();
            for (int i = 0; i < postDbList.size(); i++) {
                Post post = new Post();
                post.setId(postDbList.get(i).getId());
                post.setTitle(postDbList.get(i).getTitle());
                post.setBody(postDbList.get(i).getBody());
                postList.add(post);
            }
            adapter = new PostAdapter(postList, getContext(), PostDbFragment.this, "delete");
            binding.rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvPost.setAdapter(adapter);
        }
    }


}