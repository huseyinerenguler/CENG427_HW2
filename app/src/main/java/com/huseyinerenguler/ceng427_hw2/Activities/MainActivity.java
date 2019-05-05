package com.huseyinerenguler.ceng427_hw2.Activities;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huseyinerenguler.ceng427_hw2.Fragments.FragmentAnnouncements;
import com.huseyinerenguler.ceng427_hw2.Fragments.FragmentFoodList;
import com.huseyinerenguler.ceng427_hw2.Fragments.FragmentNews;
import com.huseyinerenguler.ceng427_hw2.R;

public class MainActivity extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final Fragment fragmentAnnouncements = new FragmentAnnouncements();
    private final Fragment fragmentFoodList = new FragmentFoodList();
    private final Fragment fragmentNews = new FragmentNews();
    private Fragment activeFragment = fragmentFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        initTabSelectedListener();
    }

    private void initFragments() {

        fragmentManager.beginTransaction().add(R.id.fragment_container, fragmentAnnouncements).hide(fragmentAnnouncements).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragmentNews).hide(fragmentNews).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragmentFoodList).hide(fragmentFoodList).commit();
        fragmentManager.beginTransaction().show(activeFragment).commit();
    }

    private void initTabSelectedListener() {

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#000000"));
        tabLayout.setTabTextColors(Color.parseColor("#80FFFFFF"), Color.parseColor("#ffffff"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                // Fragment Food List
                if (position == 0) {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentFoodList).commit();
                    activeFragment = fragmentFoodList;
                }

                // Fragment Announcements
                else if (position == 1) {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentAnnouncements).commit();
                    activeFragment = fragmentAnnouncements;
                }

                // Fragment News
                else if (position == 2) {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentNews).commit();
                    activeFragment = fragmentNews;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}