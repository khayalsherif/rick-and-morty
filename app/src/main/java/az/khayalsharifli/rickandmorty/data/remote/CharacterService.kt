package az.khayalsharifli.rickandmorty.data.remote

import az.khayalsharifli.rickandmorty.model.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterModel

    @GET("character")
    suspend fun getCharactersByName(
        @Query("name") name: String,
        @Query("page") page: Int
    ): CharacterModel

    @GET("character")
    suspend fun getCharactersByStatusAndGender(
        @Query("status") status: String,
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): CharacterModel

    @GET("character")
    suspend fun getCharactersByStatus(
        @Query("status") status: String,
        @Query("page") page: Int
    ): CharacterModel

    @GET("character")
    suspend fun getCharactersByGender(
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): CharacterModel

}