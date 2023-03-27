package com.pico.mydicitonary.feature_dictionary.data.repository

import com.pico.mydicitonary.core.utils.Resource
import com.pico.mydicitonary.feature_dictionary.data.local.WordInfoDao
import com.pico.mydicitonary.feature_dictionary.data.remote.DictionaryApi
import com.pico.mydicitonary.feature_dictionary.domain.model.WordInfo
import com.pico.mydicitonary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.toWordInfoEntity().word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
            val updatedWordInfo = dao.getWordInfos(word).map { it.toWordInfo() }
            emit(Resource.Success(updatedWordInfo))
        } catch (e: HttpException){
            emit(Resource.Error(
                message = "HttpException occurred! ${e.message}",
                data = wordInfos
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't Reach the server, Check your internet connection. ${e.message}",
                data = wordInfos
            ))
        }
    }
}