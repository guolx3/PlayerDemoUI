package com.study.playerdemoui.ui.live;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.study.playerdemoui.R;

public class LiveFragment extends Fragment {
    private LiveViewModel mLiveViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLiveViewModel = new LiveViewModel();
        View root = inflater.inflate(R.layout.fragment_live, container, false);
        final TextView textView = root.findViewById(R.id.tv_live);
        mLiveViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
