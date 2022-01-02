package com.example.autoanswerapp;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public Fragment3 fragment3;
    public FragmentManager fragmentManager = getSupportFragmentManager();
    public static TabLayout tabLayout;
    public ViewPager viewPager;
    public static FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager=(NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(!notificationManager.isNotificationPolicyAccessGranted()){
            startActivity(new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
        }

        if(getApplicationContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED
        ||getApplicationContext().checkSelfPermission(Manifest.permission.READ_CALL_LOG)!=PackageManager.PERMISSION_GRANTED
        ||getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED
        ||getApplicationContext().checkSelfPermission(Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.SEND_SMS},1);
        }

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.main_viewpage);

        tabLayout.setupWithViewPager(viewPager);

        Adapter adapter = new Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        adapter.addFragment(new Fragment1(),"main");
        adapter.addFragment(new Fragment2(),"second");

        viewPager.setAdapter(adapter);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        final boolean[] isOpen = {false};
        AnimatorSet forward = new AnimatorSet();
        forward.playTogether(
                ObjectAnimator.ofFloat(floatingActionButton,"translationX",-120),
                ObjectAnimator.ofFloat(floatingActionButton,"rotation",0,-135)

        );
        forward.setDuration(300);
        AnimatorSet backward = new AnimatorSet();
        backward.playTogether(
                ObjectAnimator.ofFloat(floatingActionButton,"translationX",0),
                ObjectAnimator.ofFloat(floatingActionButton,"rotation",-135,0)

        );
        backward.setDuration(300);


        floatingActionButton.setOnClickListener(v -> {
            if (isOpen[0]) {
                backward.start();
                fragmentCall(fragmentManager, 0);
                fragmentManager.executePendingTransactions();
                isOpen[0] = false;
            } else {
                forward.start();
                fragmentCall(fragmentManager, 1);
                isOpen[0] = true;
            }
        });

    }

    public void fragmentCall(FragmentManager fm, int mode){
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if(mode == 1){
            fragment3 = new Fragment3();
            ft.replace(R.id.relativeLayout,fragment3);
        }else if(mode == 0){
            ft.remove(fragment3);
        }
        ft.commit();
        fm.executePendingTransactions();
    }

}

