package com.study.playerdemoui.ui.localvideo;

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

public class LocalVideoFragment extends Fragment {
    private LocalVideoViewModel mLocalVideoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLocalVideoViewModel = new LocalVideoViewModel();
        View root = inflater.inflate(R.layout.fragment_loacl_video, container, false);
        TextView textView = root.findViewById(R.id.tv_local_video);
        mLocalVideoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
