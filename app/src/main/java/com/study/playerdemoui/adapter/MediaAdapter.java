package com.study.playerdemoui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.playerdemoui.R;
import com.study.playerdemoui.player.PlayerView;

public class MediaAdapter extends RecyclerView.Adapter {
    private final String[] mTexts;
    @LayoutRes private final int layout;

    public MediaAdapter(String[] texts, int layout){
        mTexts = texts;
        this.layout = layout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent,
                false);
        return new VideoItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoItemHolder videoItemHolder = (VideoItemHolder) holder;
        videoItemHolder.setText(mTexts[position]);
    }

    @Override
    public int getItemCount() {
        return mTexts.length;
    }

    public static class VideoItemHolder extends RecyclerView.ViewHolder {
        private PlayerView playerView;

        public VideoItemHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.player_view);
        }

        public void setText(String text){
            playerView.setText(text);
        }


    }

}
