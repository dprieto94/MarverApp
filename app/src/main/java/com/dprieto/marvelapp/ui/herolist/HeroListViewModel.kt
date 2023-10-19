package com.dprieto.marvelapp.ui.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.marvelapp.data.Repository
import com.dprieto.marvelapp.domain.MarvelCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _characters = MutableStateFlow(emptyList<MarvelCharacter>())
    val characters: StateFlow<List<MarvelCharacter>> get() = _characters

    init {
        getCharacters()
    }

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters().flowOn(Dispatchers.IO).collect{
                _characters.value = it
            }
        }
    }

    fun setFavorite(character: MarvelCharacter){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavorite(character)
        }
    }

}