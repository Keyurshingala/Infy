package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fargment.PostDbFragment;
import com.example.myapplication.fargment.UserDbFragment;

public class CustomDbAdapter extends FragmentStatePagerAdapter {

    public CustomDbAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UserDbFragment();

            case 1:
                return new PostDbFragment();

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "User";
        } else {
            return "Post";
        }
    }
}