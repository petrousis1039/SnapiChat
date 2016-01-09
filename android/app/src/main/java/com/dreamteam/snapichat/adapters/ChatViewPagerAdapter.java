package com.dreamteam.snapichat.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> fragmentTitleList = new ArrayList<>();
    private List<String> fragmentUID = new ArrayList<>();

    public ChatViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, String toUID) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
        fragmentUID.add(toUID);
    }

    public String getUID(int position) {
        return fragmentUID.get(position);
    }

    public int getPositionByUID(String uid) {
        for(int i=0; i< fragmentUID.size(); i++) {
            String toUID = fragmentUID.get(i);
            if(toUID.equals(uid)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
