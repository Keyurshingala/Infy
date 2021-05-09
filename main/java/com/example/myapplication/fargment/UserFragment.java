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

import com.example.myapplication.UserModel.User;
import com.example.myapplication.adapter.MyAdapter;
import com.example.myapplication.databinding.FragmentUserBinding;
import com.example.myapplication.db.UserDatabase;
import com.example.myapplication.db.Userdb;
import com.example.myapplication.service.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserFragment extends Fragment implements MyAdapter.onClickListener {

    FragmentUserBinding binding;
    MyAdapter adapter;
    UserDatabase db;
    String type="B";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = UserDatabase.getUserDatabase(getContext());

        try {
            if (getArguments().getString("btn") != null) {
                type = getArguments().getString("btn");
            } else {
                type = "A";
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        call(type);

    }

    private void call(String btn) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                adapter = new MyAdapter(response.body(), getContext(), UserFragment.this, btn);
                binding.rvUser.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvUser.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCardClick(User user) {
        Userdb userdb = new Userdb(user.getName(), user.getEmail(), user.getAddress().getCity(), user.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLng());
        if (type.equals("in")) {
            db.userDuo().saveRecord(userdb);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}