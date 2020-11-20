package com.study.playerdemoui.ui.live;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.playerdemoui.GridSpaceItemDecoration;
import com.study.playerdemoui.R;
import com.study.playerdemoui.adapter.MediaAdapter;

public class LiveFragment extends Fragment {
    private RecyclerView recyclerView;
    private final String[] mTexts = new String[]{
            "直播1", "直播2", "直播3", "直播4", "直播5", "直播6"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_live, container, false);
        recyclerView = root.findViewById(R.id.live_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(2, 10, 10));
        MediaAdapter mediaAdapter = new MediaAdapter(mTexts, R.layout.live_item);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(mediaAdapter);
        return root;
    }
}
