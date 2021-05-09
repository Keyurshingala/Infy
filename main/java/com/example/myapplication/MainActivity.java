package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.fargment.DataBaseFragment;
import com.example.myapplication.fargment.HomeFragment;
import com.example.myapplication.fargment.PostFragment;
import com.example.myapplication.fargment.UserFragment;

public class MainActivity extends AppCompatActivity {

    MainActivity context;
    ActivityMainBinding binding;
    ActionBarDrawerToggle drawerToggle;
    UserFragment userFragment = new UserFragment();
    PostFragment postFragment = new PostFragment();
    String fragmentName="Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        addFragment(new HomeFragment(),"a");

        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.include.toolBar, R.string.open, R.string.close);

        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.drwrHome:
                    fragmentName="Home";
                    addFragment(new HomeFragment(), "");
                    break;
                case R.id.drwrUser:
                    fragmentName="User";
                    addFragment(userFragment, "in");
                    break;
                case R.id.drwrPost:
                    fragmentName="Post";
                    addFragment(postFragment, "in");
                    break;
                case R.id.drwrDataBase:
                    fragmentName="Db";
                    addFragment(new DataBaseFragment(),"delete");
                    break;
            }
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (!fragmentName.equals("Home")){
            addFragment(new HomeFragment(),"");
            binding.navigationView.setCheckedItem(R.id.drwrHome);
        }else {
            super.onBackPressed();
        }
    }

    private void addFragment(Fragment fragment, String btn) {
        Bundle bundle = new Bundle();
        bundle.putString("btn", btn);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayMainScreen,fragment);
        transaction.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }
}