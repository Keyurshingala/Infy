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
import com.example.myapplication.databinding.FragmentPostBinding;
import com.example.myapplication.db.PostDatabase;
import com.example.myapplication.db.Postdb;
import com.example.myapplication.service.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostFragment extends Fragment implements PostAdapter.onClickListener {

    FragmentPostBinding binding;
    PostAdapter adapter;
    PostDatabase db;
    String button="B";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        db = PostDatabase.getPostDatabase(getContext());

        try {
            if (getArguments().getString("btn") != null) {
                button = getArguments().getString("btn");
            } else {
                button = "A";
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        call(button);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void call(String btn) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                adapter = new PostAdapter(response.body(), getContext(), PostFragment.this, btn);
                binding.rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvPost.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCardClick(Post post) {
        Postdb postdb = new Postdb(post.getId(), post.getTitle(), post.getBody());
        if (button.equals("in")) {
            db.postDao().saveRecord(postdb);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}