package com.FIEK.raportoup.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.FIEK.raportoup.fragmentet.InfoUP;
import com.FIEK.raportoup.fragmentet.RaportetMiaFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int tabsNr;

    public PageAdapter(@NonNull FragmentManager fm, int tabsNr) {
        super(fm);
        this.tabsNr = tabsNr;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RaportetMiaFragment();
            case 1:
                return new InfoUP();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNr;
    }
}
