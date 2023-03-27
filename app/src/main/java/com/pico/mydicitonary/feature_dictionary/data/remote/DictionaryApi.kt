package com.pico.mydicitonary.feature_dictionary.data.remote

import com.pico.mydicitonary.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    fun getWordInfo(@Path(value = "word") word: String): List<WordInfoDto>
}