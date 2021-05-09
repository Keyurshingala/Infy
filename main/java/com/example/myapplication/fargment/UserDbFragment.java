package com.example.myapplication.fargment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.UserModel.Address;
import com.example.myapplication.UserModel.Geo;
import com.example.myapplication.UserModel.User;
import com.example.myapplication.adapter.MyAdapter;
import com.example.myapplication.databinding.FragmentUserDbBinding;
import com.example.myapplication.db.UserDatabase;
import com.example.myapplication.db.Userdb;

import java.util.ArrayList;
import java.util.List;

public class UserDbFragment extends Fragment implements MyAdapter.onClickListener {

    FragmentUserDbBinding binding;
    MyAdapter adapter;
    List<Userdb> userDbList;
    List<User> userList;
    UserDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserDbBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = UserDatabase.getUserDatabase(getContext());
        showDb();
    }

    @Override
    public void onCardClick(User user) {
        //Userdb userdb = new Userdb(user.getName(), user.getEmail(), user.getAddress().getCity(), user.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLng());
        db.userDuo().deleteRecord(user.getEmail());
        userList.remove(user);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
    }

    private void showDb() {
        userDbList = new ArrayList<>();
        userDbList.addAll(db.userDuo().readRecord());

        if (userDbList.size() > 0) {
            userList = new ArrayList<>();
            for (int i = 0; i < userDbList.size(); i++) {
                User user = new User();
                user.setName(userDbList.get(i).getName());
                user.setEmail(userDbList.get(i).getEmail());

                Address add = new Address();
                add.setCity(userDbList.get(i).getCity());

                Geo g = new Geo();
                g.setLat(userDbList.get(i).getLat());
                g.setLng(userDbList.get(i).getLng());

                add.setGeo(g);

                user.setAddress(add);

                userList.add(user);
            }
            adapter = new MyAdapter(userList, getContext(), UserDbFragment.this, "delete");
            binding.rvUser.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvUser.setAdapter(adapter);
        }
    }


}