package rr.pockemonchallange.dataimp.repositoryimp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rr.pockemonchallange.data.model.PokemonDto
import rr.pockemonchallange.data.model.PokemonListDto
import rr.pockemonchallange.data.model.PokemonSimpleDto
import rr.pockemonchallange.data.repository.Repository
import rr.pockemonchallange.dataimp.PokeAPI


class RepositoryImp() : Repository {



    val pokeApiImp by lazy {
        PokeAPI.create()
    }

    override fun getPokemons(offset: Int,limit: Int) : MutableLiveData<PokemonListDto?>
    {
        return resultHandling(pokeApiImp.getPokemons(offset,limit),MutableLiveData<PokemonListDto?>())
    }

    override fun getPokemon(name: String) : MutableLiveData<PokemonDto?>
    {
        return resultHandling(pokeApiImp.getPokemon(name),MutableLiveData<PokemonDto?>())
    }

    fun <T> resultHandling(observable: Observable<T>, result: MutableLiveData<T?>) : MutableLiveData<T?>
    {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {out -> result.value=out},
                        {
                            error ->
                            result.value=null
                            Log.e(null,error.message)
                        }
                )
        return result
    }


}