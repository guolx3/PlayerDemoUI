package com.study.playerdemoui.player;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.study.playerdemoui.DetailActivity;
import com.study.playerdemoui.R;

public class PlayerView extends ConstraintLayout {
    private Context mContext;
    private TextView tv;
    private ImageButton playButton;
    private ImageButton settingButton;
    private ConstraintLayout controllerBottom;

    public PlayerView(@NonNull Context context) {
        super(context);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mContext = context;

        View view = LayoutInflater.from(mContext).inflate(R.layout.player_view, this);
        tv = view.findViewById(R.id.tv);
        playButton = view.findViewById(R.id.play_button);
        settingButton = view.findViewById(R.id.setting_button);
        controllerBottom = view.findViewById(R.id.controller_bottom);

        playButton.setTag(false);
        playButton.setOnClickListener(playButtonOnClick);

        settingButton.setOnClickListener(settingButtonOnClick);
    }

    public void setText(String text) {
        tv.setText(text);
    }

    private View.OnClickListener playButtonOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if((boolean)playButton.getTag()){
                playButton.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
                playButton.setTag(false);
            }else{
                playButton.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
                playButton.setTag(true);
            }
        }
    };

    private View.OnClickListener settingButtonOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("text", tv.getText());
            mContext.startActivity(intent, null);
        }
    };
}
