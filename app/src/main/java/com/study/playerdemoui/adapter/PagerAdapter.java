package com.study.playerdemoui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.study.playerdemoui.R;
import com.study.playerdemoui.ui.home.MiniVideoFragment;
import com.study.playerdemoui.ui.home.ShortVideoFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PagerAdapter extends FragmentPagerAdapter {
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragmentMap.put(0, new ShortVideoFragment());
        fragmentMap.put(1, new MiniVideoFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Objects.requireNonNull(fragmentMap.get(position));
    }

    @Override
    public int getCount() {
        return fragmentMap.size();
    }
}
