package az.khayalsharifli.rickandmorty.data

import androidx.paging.PagingData
import az.khayalsharifli.rickandmorty.model.CharacterModel
import az.khayalsharifli.rickandmorty.model.CharacterResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterRepository {

    suspend fun getPagingData(): Flow<PagingData<CharacterResult>>

    suspend fun getCharactersByName(
        name: String
    ): Flow<PagingData<CharacterResult>>

    suspend fun getCharactersByStatusAndGender(
        status: String,
        gender: String,
    ): Flow<PagingData<CharacterResult>>

    suspend fun getCharactersByStatus(
        status: String,
    ): Flow<PagingData<CharacterResult>>

    suspend fun getCharactersByGender(
        gender: String,
    ): Flow<PagingData<CharacterResult>>
}