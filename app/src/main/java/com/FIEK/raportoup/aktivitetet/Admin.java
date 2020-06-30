package com.FIEK.raportoup.aktivitetet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;

import com.FIEK.raportoup.R;
import com.FIEK.raportoup.adapter.AdminPageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final Toolbar toolbar = findViewById(R.id.toolbarAdmin);
        toolbar.setTitle(getResources().getString(R.string.raportetAdmin));

        final TabLayout tablayout;
        tablayout = findViewById(R.id.tablayoutAdmin);

        TabItem raportimetAdmin = findViewById(R.id.raportimetAdmin);
        TabItem statistika = findViewById(R.id.statistikaAdmin);

        ViewPager viewPager = findViewById(R.id.viewpagerAdmin);
        AdminPageAdapter adminPageAdapter = new AdminPageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adminPageAdapter);

        /*Sinkronizimi me Tablayout indikatorin, i cili nderron tab-at kur klikon ose swipe ne tabs te ndryshem*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Admin.this,
                            android.R.color.holo_blue_dark));
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Admin.this,
                            android.R.color.holo_red_dark));
                }

                if (tab.getPosition() == 1) {
                    tablayout.setBackgroundColor(ContextCompat.getColor(Admin.this,
                            android.R.color.holo_blue_dark));
                } else {
                    tablayout.setBackgroundColor(ContextCompat.getColor(Admin.this,
                            android.R.color.holo_red_dark));
                }
                if (tab.getPosition() == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Admin.this,
                                android.R.color.black));
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(Admin.this,
                                    android.R.color.holo_blue_dark));
                        }
                    }
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