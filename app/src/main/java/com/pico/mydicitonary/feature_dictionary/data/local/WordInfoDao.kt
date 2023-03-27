package com.pico.mydicitonary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pico.mydicitonary.feature_dictionary.data.local.entities.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert
    suspend fun insertWordInfos(wordInfos: List<WordInfoEntity>)

    @Query("DELETE FROM WordInfoEntity WHERE word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>
}