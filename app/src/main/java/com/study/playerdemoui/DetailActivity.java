package com.study.playerdemoui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.study.playerdemoui.player.PlayerView;

public class DetailActivity extends AppCompatActivity {
    private PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        playerView = findViewById(R.id.detail_player_view);
        playerView.setText((String) getIntent().getExtras().get("text"));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.activity_detail_title);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}