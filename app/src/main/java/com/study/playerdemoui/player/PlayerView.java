package com.study.playerdemoui.player;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.study.playerdemoui.DetailActivity;
import com.study.playerdemoui.R;

public class PlayerView extends ConstraintLayout {
    private final static String TAG = "PlayerView";
    private TypedArray typedArray;
    private boolean showBottomController;
    private boolean showScreenshotButton;
    private boolean showSettingButton;
    private int videoOrientation;
    private final static int VIDEO_HORIZONTAL = 0;
    private final static int VIDEO_VERTICAL = 1;

    private Context mContext;
    private TextView tv;
    private ImageButton playButton;
    private ImageButton settingButton;
    private ConstraintLayout bottomController;
    private ImageButton screenShotButton;
    private ImageButton fullScreenButton;

    // operator
    private final int VERTICAL = 3;
    private final int HORIZONTAL = 4;
    private final int LEFT = 5;
    private final int RIGHT = 6;
    private final int OPERATE_ORIENTATION = 0;
    private final int OPERATE_AREA = 1;
    private final float OPERATE_THRESHOLD = 30f;
    private int[] operateType;
    private float startX = 0f;
    private float startY = 0f;
    private long preUpTime = 0l;
    private boolean doingVerticalOperator = false;
    private boolean finishVerticalOperator = false;
    private boolean doingHorizontalOperator = false;
    private boolean finishHorizontalOperator = false;

    private LinearLayout volumeController;
    private SeekBar volumeSeekBar;
    private final int VOLUME_STEP = 5;
    private final int VOLUME_MAX = 100;

    private final int HIDE_VOLUME_CONTROLLER = 1;
    private MyHandler myHandler = new MyHandler();
    private class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case HIDE_VOLUME_CONTROLLER:
                    hideVolumeController();
                    break;
            }
        }
    }

    public PlayerView(@NonNull Context context) {
        super(context);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayerView);
        showBottomController = typedArray.getBoolean(R.styleable.PlayerView_showBottomController, true);
        showScreenshotButton = typedArray.getBoolean(R.styleable.PlayerView_showScreenshotButton, true);
        showSettingButton = typedArray.getBoolean(R.styleable.PlayerView_showSettingButton, true);
        videoOrientation = typedArray.getInt(R.styleable.PlayerView_videoOrientation, 0);
        init(context);
    }

    public void init(Context context){
        mContext = context;

        View view = LayoutInflater.from(mContext).inflate(R.layout.player_view, this);
        tv = view.findViewById(R.id.tv);
        playButton = view.findViewById(R.id.play_button);
        settingButton = view.findViewById(R.id.setting_button);
        screenShotButton = view.findViewById(R.id.screenshot_button);
        if(!showSettingButton){
            settingButton.setVisibility(INVISIBLE);
        }
        if(!showScreenshotButton){
            screenShotButton.setVisibility(INVISIBLE);
        }
        bottomController = view.findViewById(R.id.controller_bottom);
        if(!showBottomController){
            bottomController.setVisibility(INVISIBLE);
        }
        volumeController = view.findViewById(R.id.volume_controller);
        volumeSeekBar = view.findViewById(R.id.volume_seek_bar);
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

    private void determineOperateType(float startX, float startY, float endX, float endY){
        operateType = new int[2];
        if(Math.abs(startX - endX) > OPERATE_THRESHOLD && Math.abs(startY - endY) < OPERATE_THRESHOLD){
            operateType[OPERATE_ORIENTATION] = HORIZONTAL;
        }
        if(Math.abs(startY - endY) > OPERATE_THRESHOLD && Math.abs(startX - endX) < OPERATE_THRESHOLD){
            operateType[OPERATE_ORIENTATION] = VERTICAL;
        }

        Point point = new Point();
        mContext.getDisplay().getSize(point);
        float screenWidth = point.x;
        if(startX < screenWidth / 2){
            operateType[OPERATE_AREA] = LEFT;
        }else{
            operateType[OPERATE_AREA] = RIGHT;
        }
    }

    private void hideVolumeController(){
        volumeController.setVisibility(GONE);
    }

    private void showVolumeController(){
        volumeController.setVisibility(VISIBLE);
    }

    private void changeVolume(float range){
        int currentPosition = volumeSeekBar.getProgress();
        int targetPosition;
        if(range < 0){
            targetPosition = Math.min(currentPosition + VOLUME_STEP, VOLUME_MAX);
        }else{
            targetPosition = Math.max(currentPosition - VOLUME_STEP, 0);
        }
        volumeSeekBar.setProgress(targetPosition);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent: ACTION_DOWN");
                //重制操作类型
                doingHorizontalOperator =false;
                doingVerticalOperator = false;
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: ACTION_MOVE");
                float endX = event.getX();
                float endY = event.getY();
                determineOperateType(startX, startY, endX, endY);
                if(operateType[OPERATE_ORIENTATION] == HORIZONTAL){
                    //do something
                }else if(operateType[OPERATE_ORIENTATION] == VERTICAL){
                    if(!doingHorizontalOperator){
                        doingVerticalOperator = true;
                        if(operateType[OPERATE_AREA] == RIGHT){
                            if(volumeController.getVisibility() == GONE){
                                showVolumeController();
                            }
                            changeVolume(endY - startY);
                        }
                        startX = endX;
                        startY = endY;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: ACTION_UP");
                myHandler.sendEmptyMessageDelayed(HIDE_VOLUME_CONTROLLER, 3000);
                break;
        }
        return true;
    }

    private Activity scanForActivity(Context context){
        if(context == null) return null;
        if(context instanceof Activity){
            return (Activity) context;
        }else if(context instanceof ContextWrapper){
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private void hideSystemUI(Activity activity) {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
