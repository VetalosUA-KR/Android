package com.example.navigationeditortest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    public void onFragment1NextClick(View view) {
        navController.navigate(R.id.fragment2);
    }

    public void onFragment1BackClick(View view) {}


    public void onFragment2NextClick(View view) {
        navController.navigate(R.id.fragment3);
    }

    public void onFragment2BackClick(View view) {
        navController.popBackStack();
    }


    public void onFragment3NextClick(View view) {}

    public void onFragment3BackClick(View view) {
        navController.popBackStack();
    }
}