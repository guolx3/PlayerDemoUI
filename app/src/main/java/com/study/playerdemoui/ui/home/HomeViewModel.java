package com.study.playerdemoui.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public HomeViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("hello home fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
