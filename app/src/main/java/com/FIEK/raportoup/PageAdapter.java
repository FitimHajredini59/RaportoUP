package com.FIEK.raportoup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int tabsNr;

    public PageAdapter(@NonNull FragmentManager fm, int tabsNr) {
        super(fm);
        this.tabsNr = tabsNr;
    }

    /*Metoda per inicializimin e fragmenteve per Android TabLayout*/
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RaportetMiaFragment();
            case 1:
                return new NdihmaFragment();
            default:
                return null;
        }
    }

    /*Metoda per kthimin e numrit te Tabs qe shfaqen ne Android TabLayout*/
    @Override
    public int getCount() {
        return tabsNr;
    }
}
