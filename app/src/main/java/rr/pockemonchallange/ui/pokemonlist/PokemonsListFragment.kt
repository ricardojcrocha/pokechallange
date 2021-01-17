package rr.pockemonchallange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pokemons_list_fragment.*
import rr.pockemonchallange.business.PokemonService
import rr.pockemonchallange.businessimp.PokemonServiceImp
import rr.pockemonchallange.data.model.PokemonListDto
import rr.pockemonchallange.data.model.PokemonSimpleDto
import rr.pockemonchallange.ui.pokemonlist.PokemonListAdapter

class PokemonsListFragment : Fragment(),PokemonListAdapter.ClickHandler {

    companion object {
        fun newInstance() = PokemonsListFragment()
    }

    private lateinit var viewModel: PokemonsListViewModel

    private lateinit var pokemonService: PokemonService

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokemons_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonsListViewModel::class.java)

        pokemonService= PokemonServiceImp()

        rvPokemonList.layoutManager=LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        rvPokemonList.adapter = PokemonListAdapter(this)



        viewModel.pokemonList.observe(this, { t ->
            (rvPokemonList.adapter as PokemonListAdapter).updateList(t.results.toList())
        })

        btnNext.setOnClickListener(View.OnClickListener { view ->  getNextPokemons()})

        btnPrevious.setOnClickListener(View.OnClickListener { view ->  getPreviousPokemons()})


        getPokemons(viewModel.offset,viewModel.limit)

    }

    private fun getNextPokemons() {
        var offset=viewModel.getNextOffset()
        if (offset>0)
        {
            btnPrevious.visibility=View.VISIBLE
        }
        if (offset+viewModel.limit>= viewModel.pokemonList.value?.count ?: 0)
        {
            btnNext.visibility=View.INVISIBLE
        }
        getPokemons(offset,viewModel.limit)
    }

    private fun getPreviousPokemons() {
        var offset=viewModel.getPreviousOffset()
        if (offset==0)
        {
            btnPrevious.visibility=View.INVISIBLE
        }
        if (offset+viewModel.limit< viewModel.pokemonList.value?.count ?: 0)
        {
            btnNext.visibility=View.VISIBLE
        }
        getPokemons(offset,viewModel.limit)
    }

    private fun getPokemons(offset: Int, limit: Int) {
        val pokemons = pokemonService.getPokemons(offset,limit)
        observeApiCall(pokemons)
    }

    private fun observeApiCall(pokemons: MutableLiveData<PokemonListDto?>)
    {
        pokemons.observe(this, { t ->
            if (t != null && !t.results.isEmpty()) {
                viewModel.pokemonList.postValue(t)
            }
        })
    }

    override fun onClick(pokemon: PokemonSimpleDto) {
        Toast.makeText(activity,pokemon.name,Toast.LENGTH_SHORT).show()
    }

}