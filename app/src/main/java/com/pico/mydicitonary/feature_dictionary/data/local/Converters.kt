package com.pico.mydicitonary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.pico.mydicitonary.feature_dictionary.data.utils.JsonParser
import com.pico.mydicitonary.feature_dictionary.domain.model.Meaning

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(meanings, object : TypeToken<ArrayList<Meaning>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun toStringListJson(list: List<String>): String {
        return jsonParser.toJson(list, object : TypeToken<ArrayList<String>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun fromStringListJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }
}