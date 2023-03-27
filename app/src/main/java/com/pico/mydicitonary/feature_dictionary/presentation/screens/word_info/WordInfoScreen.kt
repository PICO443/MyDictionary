package com.pico.mydicitonary.feature_dictionary.presentation.screens.word_info

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordInfoScreen(viewModel: WordInfoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val uiState = viewModel.uiState
    Scaffold() { paddingValues ->
        LazyColumn(contentPadding = paddingValues){

        }
    }
}