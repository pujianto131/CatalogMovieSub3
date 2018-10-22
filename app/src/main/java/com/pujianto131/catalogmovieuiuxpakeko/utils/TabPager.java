package com.pujianto131.catalogmovieuiuxpakeko.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pujianto131.catalogmovieuiuxpakeko.fitur.FavoritFragment;
import com.pujianto131.catalogmovieuiuxpakeko.fitur.NowPlayingFragment;
import com.pujianto131.catalogmovieuiuxpakeko.fitur.UpComingFragment;

public class TabPager extends FragmentPagerAdapter {

    private static final int NUM_ITEMS = 3;

    public TabPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NowPlayingFragment();
            case 1:
                return new UpComingFragment();
            case 3:
                return new FavoritFragment();

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
