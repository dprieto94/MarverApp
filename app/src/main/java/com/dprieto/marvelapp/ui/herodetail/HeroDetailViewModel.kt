package com.dprieto.marvelapp.ui.herodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprieto.marvelapp.data.Repository
import com.dprieto.marvelapp.domain.MarvelMedia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _series = MutableStateFlow(emptyList<MarvelMedia>())
    val series: StateFlow<List<MarvelMedia>> get() = _series

    private val _comics = MutableStateFlow(emptyList<MarvelMedia>())
    val comics: StateFlow<List<MarvelMedia>> get() = _comics


    fun getMedia(characterId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacterSeries(characterId).flowOn(Dispatchers.IO).collect{
                _series.value = it
            }

            repository.getCharacterComics(characterId).flowOn(Dispatchers.IO).collect{
                _comics.value = it
            }
        }
    }

}