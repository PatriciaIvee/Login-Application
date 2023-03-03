package ph.kodego.leones.patricia.ivee.apis

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.leones.patricia.ivee.apis.adapter.PokemonAdapter
import ph.kodego.leones.patricia.ivee.apis.api.PokemonAPIClient
import ph.kodego.leones.patricia.ivee.apis.databinding.FragmentPokemonListBinding
import ph.kodego.leones.patricia.ivee.apis.model.Pokemon
import ph.kodego.leones.patricia.ivee.apis.model.PokemonListResponse
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class PokemonListFragment : Fragment() {

    var pokemonAdapter:PokemonAdapter? = null
    var pokemonList: ArrayList<Pokemon> = ArrayList()
    var binding:FragmentPokemonListBinding? = null
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        var view = binding!!.root

        pokemonAdapter = PokemonAdapter(requireActivity().applicationContext,pokemonList)

        binding!!.pokemonList.layoutManager = LinearLayoutManager(requireActivity().applicationContext,
            LinearLayoutManager.VERTICAL, false)
        binding!!.pokemonList.adapter = pokemonAdapter

        getData()

        return view
    }

    private fun getData() {
       val call: Call<PokemonListResponse> =
           PokemonAPIClient.getPokemonData.getList(0,100)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>, t:Throwable){
                Log.d("API CALL", "Failed API CALL")
            }

            override fun onResponse(call: Call<PokemonListResponse>,
                                    response: Response<PokemonListResponse>) {

                var response: PokemonListResponse = response!!.body()!!
                pokemonAdapter!!.setList(response.pokemonList)

                var pokelist = response.pokemonList
                for (pokemon in pokelist){
                    Log.d("API CALL", pokemon.name)
                }
                Log.d("API CALL", "Pokemon list size: " + pokemonList.size)
            }
        })
    }
}