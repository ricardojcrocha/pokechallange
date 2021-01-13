package rr.pockemonchallange

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rr.pockemonchallange.business.PokemonService
import rr.pockemonchallange.business.PokemonService.Callback
import rr.pockemonchallange.businessimp.PokemonServiceImp
import rr.pockemonchallange.data.model.PokemonListDto

class PokemonsListFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonsListFragment()
    }

    private lateinit var viewModel: PokemonsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokemons_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonService: PokemonService = PokemonServiceImp()

        pokemonService.getPokemons(callback = object: Callback<PokemonListDto>{
            override fun onResult(result: PokemonListDto) {
                Log.d("POKEMONS","SUCESS")
            }

            override fun onError(errorMessage: String) {
                Log.d("POKEMONS",errorMessage)
            }
        })
    }
}