package com.pico.mydicitonary.feature_dictionary.data.remote.dto

import com.pico.mydicitonary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
) {
    fun toMeaning(): Meaning {
     return Meaning(
         definitions.map { it.toDefinition() },
         partOfSpeech,
     )
    }
}