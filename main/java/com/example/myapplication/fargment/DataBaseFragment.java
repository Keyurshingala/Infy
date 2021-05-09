package com.example.myapplication.fargment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.adapter.CustomAdapter;
import com.example.myapplication.adapter.CustomDbAdapter;
import com.example.myapplication.databinding.FragmentDataBaseBinding;

public class DataBaseFragment extends Fragment {

    FragmentDataBaseBinding binding;
    CustomDbAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDataBaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CustomDbAdapter(getActivity().getSupportFragmentManager());
        binding.ViewPager.setAdapter(adapter);
        binding.tabLayOut.setupWithViewPager(binding.ViewPager);
    }
}
