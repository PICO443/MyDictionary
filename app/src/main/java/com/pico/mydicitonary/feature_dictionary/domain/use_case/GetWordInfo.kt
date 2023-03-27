package com.pico.mydicitonary.feature_dictionary.domain.use_case

import com.pico.mydicitonary.core.utils.Resource
import com.pico.mydicitonary.feature_dictionary.domain.model.WordInfo
import com.pico.mydicitonary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val wordInfoRepository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        return if (word.isBlank()) {
            flow { }
        } else {
            wordInfoRepository.getWordInfo(word)
        }
    }
}