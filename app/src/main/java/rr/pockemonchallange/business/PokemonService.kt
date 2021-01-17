package rr.pockemonchallange.business

import androidx.lifecycle.MutableLiveData
import rr.pockemonchallange.data.model.PokemonDto
import rr.pockemonchallange.data.model.PokemonListDto

interface PokemonService
{
    fun getPokemons(offset: Int,limit: Int): MutableLiveData<PokemonListDto?>

    fun getPokemon(name: String): MutableLiveData<PokemonDto?>
}