package com.study.playerdemoui.ui.localvideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.study.playerdemoui.R;

public class LocalVideoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_loacl_video, container, false);
        TextView textView = root.findViewById(R.id.tv_local_video);
        String text = "hello local video fragment!";
        textView.setText(text);
        return root;
    }
}
