package com.example.walk_in_sale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentName = new ArrayList<>();
    private final List<String> fragmentTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    public Fragment getItem(int position) {
        return fragmentName.get(position);
    }

    public int getCount() {
        return fragmentTitle.size();
    }

    @NonNull
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    //    Add Fragment Manager
    public void AddFragment(Fragment fragment, String title) {
        fragmentName.add(fragment);
        fragmentTitle.add(title);
    }

}
