package com.FIEK.raportoup.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.FIEK.raportoup.fragmentet.Raportimet;
import com.FIEK.raportoup.fragmentet.Statistika;

public class AdminPageAdapter extends FragmentPagerAdapter {

    private int tabsNr;

    public AdminPageAdapter(@NonNull FragmentManager fm, int tabsNr) {
        super(fm);
        this.tabsNr = tabsNr;
    }

    /*Metoda per inicializimin e fragmenteve per Android TabLayout*/
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Raportimet();
            case 1:
                return new Statistika();
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
