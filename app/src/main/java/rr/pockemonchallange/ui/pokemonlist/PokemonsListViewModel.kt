package rr.pockemonchallange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rr.pockemonchallange.data.model.PokemonListDto

class PokemonsListViewModel : ViewModel() {

    var offset: Int = 0
    var limit: Int = 20

    var pokemonList: MutableLiveData<PokemonListDto> = MutableLiveData(PokemonListDto(0,"","",ArrayList()))

    fun getNextOffset(): Int {
        offset=offset+limit
        return offset
    }

    fun getPreviousOffset(): Int {
        offset=offset-limit
        if (offset<0)
        {
            offset=0
        }
        return offset
    }

}