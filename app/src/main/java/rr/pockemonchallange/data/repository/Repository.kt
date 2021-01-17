package rr.pockemonchallange.data.repository

import androidx.lifecycle.MutableLiveData
import rr.pockemonchallange.data.model.PokemonDto
import rr.pockemonchallange.data.model.PokemonListDto
import rr.pockemonchallange.dataimp.PokeAPI

interface Repository
{

    fun getPokemons(offset: Int,limit: Int) : MutableLiveData<PokemonListDto?>

    fun getPokemon(name: String) : MutableLiveData<PokemonDto?>

}