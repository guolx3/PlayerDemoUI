package com.study.playerdemoui.ui.live;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public LiveViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("hello live fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
