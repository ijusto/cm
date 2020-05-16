package com.example.roomwordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    /* An Activity can be destroyed and created many times during the lifecycle of a ViewModel, such
       as when the device is rotated. If you store a reference to the Activity in the ViewModel, you
       end up with references that point to the destroyed Activity. This is a memory leak. If you
       need the application context, use AndroidViewModel, as shown in this practical. */
    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }
}