package com.example.testapp1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment.This is home fragment. This is home fragment.");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> setText(String str){
        mText.postValue(str);

        return mText;
    }
}