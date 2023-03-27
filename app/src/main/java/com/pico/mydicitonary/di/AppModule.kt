package com.pico.mydicitonary.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.pico.mydicitonary.feature_dictionary.data.local.Converters
import com.pico.mydicitonary.feature_dictionary.data.local.WordInfoDatabase
import com.pico.mydicitonary.feature_dictionary.data.remote.DictionaryApi
import com.pico.mydicitonary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.pico.mydicitonary.feature_dictionary.data.utils.GsonParser
import com.pico.mydicitonary.feature_dictionary.domain.repository.WordInfoRepository
import com.pico.mydicitonary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordInfoDatabase(@ApplicationContext ctx: Context): WordInfoDatabase {
        return Room.databaseBuilder(ctx, WordInfoDatabase::class.java, "word_info_db")
            .addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit
            .Builder()
            .baseUrl("https://api.dictionaryapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, db: WordInfoDatabase): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.wordInfoDao)
    }

    @Provides
    @Singleton
    fun provideGetWordInfo(wordInfoRepository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(wordInfoRepository)
    }

}