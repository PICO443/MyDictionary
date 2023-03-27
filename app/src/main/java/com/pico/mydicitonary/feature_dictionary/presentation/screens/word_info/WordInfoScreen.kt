package com.pico.mydicitonary.feature_dictionary.presentation.screens.word_info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.mydicitonary.feature_dictionary.presentation.screens.word_info.components.WordInfoItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordInfoScreen(viewModel: WordInfoViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.onEach {
            when (it) {
                is WordInfoViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = it.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }.launchIn(this)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = viewModel.searchQuery,
                onValueChange = viewModel::onSearch,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(contentPadding = paddingValues) {
                itemsIndexed(uiState.wordInfos) { index, wordInfo ->
                    WordInfoItem(wordInfo = wordInfo)
                    if (index < uiState.wordInfos.size - 1 ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}