package com.example.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    /* Add a word that already exists in the list. What happens? Does your app crash?
       Your app uses the word itself as the primary key, and each primary key must be unique.
       You can specify a conflict strategy to tell your app what to do when the user tries to add an
       existing word. */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    /* LiveData, which is a lifecycle library class for data observation, can help your app respond
       to data changes. If you use a return value of type LiveData in your method description, Room
       generates all necessary code to update the LiveData when the database is updated. */
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
