package com.dummy.myapplication.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dummy.myapplication.data.local.entity.Word;
import com.dummy.myapplication.data.local.repo.WordRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final WordRepository mRepository;
    private final LiveData<List<Word>> mAllWords;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }


    public void deleteWord(Word word) {
        mRepository.delete(word);
    }

    public void removeWord(Word word) {
        mRepository.remove(word);
    }
}