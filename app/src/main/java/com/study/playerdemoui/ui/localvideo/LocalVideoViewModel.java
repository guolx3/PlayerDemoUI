package com.study.playerdemoui.ui.localvideo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocalVideoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public LocalVideoViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("hello local video fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
