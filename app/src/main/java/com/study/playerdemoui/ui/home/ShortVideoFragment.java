package com.study.playerdemoui.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.playerdemoui.R;
import com.study.playerdemoui.adapter.MediaAdapter;

public class ShortVideoFragment extends Fragment implements RecyclerView.RecyclerListener{
    private RecyclerView recyclerView;
    private MediaAdapter mediaAdapter;

    private String[] texts = new String[]{
            "视频1", "视频2", "视频3", "视频4", "视频5", "视频6"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_short_video, container,
                false);

        recyclerView = view.findViewById(R.id.recycler_view_short_video);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mediaAdapter = new MediaAdapter(texts);
        recyclerView.setAdapter(mediaAdapter);
        recyclerView.setRecyclerListener(this);
        return view;
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        MediaAdapter.VideoItemHolder videoItemHolder = (MediaAdapter.VideoItemHolder) holder;
    }
}
