package com.pico.mydicitonary.feature_dictionary.presentation.screens.word_info.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pico.mydicitonary.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(wordInfo: WordInfo, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = wordInfo.word, style = MaterialTheme.typography.titleLarge)
        Text(
            text = wordInfo.phonetic,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))
        wordInfo.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { i, definition ->
                Text(text = "$i. ${definition.definition}")
                definition.example?.let { example ->
                    Text(text = example)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}