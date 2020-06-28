package com.FIEK.raportoup.aktivitetet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;

import com.FIEK.raportoup.adapter.PageAdapter;
import com.FIEK.raportoup.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RaportetMia<Tablayout> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raportet_mia);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.raportet_mia));

        final TabLayout tablayout;
        tablayout = findViewById(R.id.tablayout);

        TabItem raportetMia = findViewById(R.id.raportetmia);
        TabItem ndihma = findViewById(R.id.ndihma);

        ViewPager viewPager = findViewById(R.id.viewpager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        /*Sinkronizimi me Tablayout indikatorin, i cili nderron tab-at kur klikon ose swipe ne tabs te ndryshem*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(RaportetMia.this,
                            android.R.color.darker_gray));
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(RaportetMia.this,
                            android.R.color.holo_orange_dark));
                }

                if (tab.getPosition() == 1) {
                    tablayout.setBackgroundColor(ContextCompat.getColor(RaportetMia.this,
                            android.R.color.darker_gray));
                } else {
                    tablayout.setBackgroundColor(ContextCompat.getColor(RaportetMia.this,
                            android.R.color.holo_orange_dark));
                }
                if (tab.getPosition() == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(RaportetMia.this,
                                android.R.color.darker_gray));
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(RaportetMia.this,
                                    android.R.color.darker_gray));
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