package rr.pockemonchallange.dataimp.repositoryimp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rr.pockemonchallange.data.model.PokemonListDto
import rr.pockemonchallange.data.model.PokemonSimpleDto
import rr.pockemonchallange.data.repository.Repository
import rr.pockemonchallange.dataimp.PokeAPI


class RepositoryImp() : Repository {



    val pokeApiImp by lazy {
        PokeAPI.create()
    }

    override fun getPokeApi(): PokeAPI {
        return pokeApiImp
    }

    fun getPokemons() : MutableLiveData<PokemonListDto?>
    {
        val result: MutableLiveData<PokemonListDto?> = MutableLiveData(PokemonListDto(-1,"","",ArrayList<PokemonSimpleDto>()))

        pokeApiImp.getPokemons()
            .subscribeOn(Schedulers.io())
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