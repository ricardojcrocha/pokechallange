package rr.pockemonchallange.businessimp

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rr.pockemonchallange.business.PokemonService
import rr.pockemonchallange.data.model.PokemonDto
import rr.pockemonchallange.data.model.PokemonListDto
import rr.pockemonchallange.dataimp.repositoryimp.RepositoryImp

class PokemonServiceImp : PokemonService {

    val repository by lazy { RepositoryImp() }

    override fun getPokemons(offset: Int,limit: Int): MutableLiveData<PokemonListDto?> {
        return repository.getPokemons(offset,limit)
    }

    override fun getPokemon(name: String): MutableLiveData<PokemonDto?>
    {
        return repository.getPokemon(name)
    }
}