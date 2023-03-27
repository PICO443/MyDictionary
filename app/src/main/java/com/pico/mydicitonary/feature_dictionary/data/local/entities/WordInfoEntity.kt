package com.pico.mydicitonary.feature_dictionary.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pico.mydicitonary.feature_dictionary.domain.model.Meaning
import com.pico.mydicitonary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings,
            phonetic,
            sourceUrls,
            word
        )
    }
}