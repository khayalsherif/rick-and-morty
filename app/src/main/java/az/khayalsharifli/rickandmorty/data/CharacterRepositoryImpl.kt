package az.khayalsharifli.rickandmorty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import az.khayalsharifli.rickandmorty.data.pagingsource.*
import az.khayalsharifli.rickandmorty.data.remote.CharacterService
import az.khayalsharifli.rickandmorty.model.CharacterResult
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val service: CharacterService) :
    CharacterRepository {
    override suspend fun getPagingData(): Flow<PagingData<CharacterResult>> {
        val data = Pager(PagingConfig(pageSize = 1)) {
            CharacterPagingSource(service)
        }.flow
        return data
    }

    override suspend fun getCharactersByName(name: String): Flow<PagingData<CharacterResult>> {
        val data = Pager(PagingConfig(pageSize = 1)) {
            CharacterByNamePagingSource(service = service, name = name)
        }.flow
        return data
    }

    override suspend fun getCharactersByStatusAndGender(
        status: String,
        gender: String
    ): Flow<PagingData<CharacterResult>> {
        val data = Pager(PagingConfig(pageSize = 1)) {
            CharacterByStatusAndGenderPagingSource(service = service, gender = gender, status = status)
        }.flow
        return data
    }

    override suspend fun getCharactersByStatus(
        status: String
    ): Flow<PagingData<CharacterResult>> {
        val data = Pager(PagingConfig(pageSize = 1)) {
            CharacterByStatusPagingSource(service = service, status = status)
        }.flow
        return data
    }

    override suspend fun getCharactersByGender(
        gender: String
    ): Flow<PagingData<CharacterResult>> {
        val data = Pager(PagingConfig(pageSize = 1)) {
            CharacterByGenderPagingSource(service = service, gender = gender)
        }.flow
        return data
    }
}