package com.example.testorangapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testorangapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    private void toggleFab() {
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.appBarMain.fabPost, "translationY", 0f).start();
            ObjectAnimator.ofFloat(binding.appBarMain.fabAlarm, "translationY", 0f).start();
            binding.appBarMain.fabPost.setVisibility(View.GONE);
            binding.appBarMain.fabAlarm.setVisibility(View.GONE);
            binding.appBarMain.fabMain.setImageResource(R.drawable.ic_fab_add);
        } else {
            binding.appBarMain.fabPost.setVisibility(View.VISIBLE);
            binding.appBarMain.fabAlarm.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(binding.appBarMain.fabPost, "translationY", -200f).start();
            ObjectAnimator.ofFloat(binding.appBarMain.fabAlarm, "translationY", -350f).start();
            binding.appBarMain.fabMain.setImageResource(R.drawable.ic_fab_close);
        }
        isFabOpen = !isFabOpen;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}