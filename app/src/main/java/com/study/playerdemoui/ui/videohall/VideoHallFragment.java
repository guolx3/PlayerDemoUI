package com.study.playerdemoui.ui.videohall;

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

public class VideoHallFragment extends Fragment {
    private VideoHallViewModel mVideoHallViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mVideoHallViewModel = new VideoHallViewModel();
        View root = inflater.inflate(R.layout.fragment_video_hall, container, false);
        TextView textView = root.findViewById(R.id.tv_video_hall);
        mVideoHallViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
