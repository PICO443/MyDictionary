package com.pico.mydicitonary.feature_dictionary.presentation.screens.word_info

import com.pico.mydicitonary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(val wordInfos: List<WordInfo> = emptyList(), val isLoading: Boolean = false)
