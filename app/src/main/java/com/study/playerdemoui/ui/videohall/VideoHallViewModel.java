package com.study.playerdemoui.ui.videohall;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VideoHallViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public VideoHallViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("hello video hall fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }

}
