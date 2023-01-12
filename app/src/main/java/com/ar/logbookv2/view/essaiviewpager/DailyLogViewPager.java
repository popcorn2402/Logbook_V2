package com.ar.logbookv2.view.essaiviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ar.logbookv2.R;
import com.ar.logbookv2.viewmodel.DailyLogViewModel;

public class DailyLogViewPager extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private DailyLogViewModel mDailyLogViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_log_view_pager);

        //mDailyLogViewModel = new ViewModelProvider(this).get(DailyLogViewModel.class);

        Intent intent = getIntent();

        int mood = intent.getIntExtra("Mood", 0);

        viewPager2 = findViewById(R.id.viewpager);
        ViewPagerAdapter viewPager2Adapter = new ViewPagerAdapter(new ViewPagerAdapter.DailyLogDiff());
        viewPager2.setAdapter(viewPager2Adapter);

        // To get swipe event of viewpager2
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                Toast.makeText(DailyLogViewPager.this, "Scrolled", Toast.LENGTH_SHORT).show();
            }

            // triggered when you select a new page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Toast.makeText(DailyLogViewPager.this, "Selected", Toast.LENGTH_SHORT).show();
            }

            // triggered when there is
            // scroll state will be changed
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

                Toast.makeText(DailyLogViewPager.this, "ScrollState", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
