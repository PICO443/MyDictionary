package com.pico.mydicitonary.feature_dictionary.presentation.screens.word_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pico.mydicitonary.core.utils.Resource
import com.pico.mydicitonary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class WordInfoViewModel(private val getWordInfo: GetWordInfo) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var uiState by mutableStateOf(WordInfoState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String){
        searchQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(searchQuery).onEach {
                when(it){
                    is Resource.Success -> {
                        uiState = uiState.copy(wordInfos = it.data, isLoading = false)
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(wordInfos = it.data ?: emptyList(), isLoading = true)
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(wordInfos = it.data ?: emptyList(), isLoading = false)
                        _eventFlow.emit(UiEvent.ShowSnackBar(message =it.message))
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UiEvent {
        class ShowSnackBar(val message: String): UiEvent()
    }
}