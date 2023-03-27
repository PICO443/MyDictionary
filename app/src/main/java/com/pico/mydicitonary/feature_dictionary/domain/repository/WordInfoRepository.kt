package com.pico.mydicitonary.feature_dictionary.domain.repository

import com.pico.mydicitonary.core.utils.Resource
import com.pico.mydicitonary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}