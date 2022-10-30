package az.khayalsharifli.rickandmorty.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import az.khayalsharifli.rickandmorty.data.remote.CharacterService
import az.khayalsharifli.rickandmorty.model.CharacterResult

class CharacterByStatusAndGenderPagingSource(
    private val service: CharacterService,
    private val gender: String,
    private val status: String
) : PagingSource<Int, CharacterResult>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, CharacterResult> {

        return try {
            val currentPage = params.key ?: 1
            val response =
                service.getCharactersByStatusAndGender(
                    gender = gender,
                    status = status, page = currentPage
                )
            val responseData = mutableListOf<CharacterResult>()
            val data = response.results
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}