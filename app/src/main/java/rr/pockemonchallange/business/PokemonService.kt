package rr.pockemonchallange.business

import androidx.lifecycle.MutableLiveData
import rr.pockemonchallange.data.model.PokemonListDto

interface PokemonService
{
    fun getPokemons(): MutableLiveData<PokemonListDto?>
}