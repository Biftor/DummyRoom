package com.dummy.myapplication.data.local.repo;


import android.app.Application;
import androidx.lifecycle.LiveData;

import com.dummy.myapplication.data.local.dao.WordDao;
import com.dummy.myapplication.data.local.db.WordRoomDatabase;
import com.dummy.myapplication.data.local.entity.Word;

import java.util.List;

public class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
    }

    public void remove(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.remove(word.getWord()));
    }

    public void delete(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.delete(word));
    }
}
