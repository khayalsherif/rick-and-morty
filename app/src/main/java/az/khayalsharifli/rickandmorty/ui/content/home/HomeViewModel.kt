package az.khayalsharifli.rickandmorty.ui.content.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import az.khayalsharifli.rickandmorty.base.BaseViewModel
import az.khayalsharifli.rickandmorty.data.CharacterRepository
import az.khayalsharifli.rickandmorty.model.CharacterResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CharacterRepository
) : BaseViewModel() {

    private var _response = MutableLiveData(emptyFlow<PagingData<CharacterResult>>())
    val response: LiveData<Flow<PagingData<CharacterResult>>>
        get() = _response

    init {
        getAllData()
    }

    fun getAllData() {
        viewModelScope.launch {
            _response.value = repository.getPagingData().cachedIn(viewModelScope)
        }
    }

    fun getDataByName(name: String) {
        viewModelScope.launch {
            _response.value = emptyFlow()
            _response.value = repository.getCharactersByName(name).cachedIn(viewModelScope)
        }
    }

    fun getDataByGender(gender: String) {
        viewModelScope.launch {
            _response.value = emptyFlow()
            _response.value = repository.getCharactersByGender(gender).cachedIn(viewModelScope)
        }
    }

    fun getDataByStatus(status: String) {
        viewModelScope.launch {
            _response.value = emptyFlow()
            _response.value = repository.getCharactersByStatus(status).cachedIn(viewModelScope)
        }
    }

    fun getDataByStatusAndGender(status: String, gender: String) {
        viewModelScope.launch {
            _response.value = emptyFlow()
            _response.value =
                repository.getCharactersByStatusAndGender(status = status, gender = gender)
        }
    }

}