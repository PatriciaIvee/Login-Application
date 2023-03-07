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

        var currentPageNumber = 1
//        var pokemonsPerPage =  binding!!.pokemonCountText.text.toString().toIntOrNull()

        binding!!.btnNext.setOnClickListener {
//            val currentPageNumber = pokemonsPerPage.toIntOrNull() ?: return@setOnClickListener
            val pokemonsPerPage = binding!!.pokemonCountText.text.toString().toIntOrNull() ?: return@setOnClickListener
            if (pokemonsPerPage > 0) {
                currentPageNumber++
                val offset = (currentPageNumber - 1) * pokemonsPerPage
                val call: Call<PokemonListResponse> = PokemonAPIClient.getPokemonData.getList(offset, pokemonsPerPage)
                call.enqueue(object : Callback<PokemonListResponse> {
                    override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                        Log.d("API CALL", "Failed API CALL")
                    }

                    override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                        var response: PokemonListResponse = response!!.body()!!
                        pokemonAdapter!!.setList(response.pokemonList)
                        Log.d("API CALL", "Pokemon list size: " + response.pokemonList.size)
                    }
                })
            }
        }

        binding!!.btnBack.setOnClickListener {
            if (currentPageNumber > 1) { // check if current page number is not less than 1
                currentPageNumber--
                val pokemonsPerPage = binding!!.pokemonCountText.text.toString().toIntOrNull() ?: return@setOnClickListener
                if (pokemonsPerPage > 0) {
                    val offset = (currentPageNumber - 1) * pokemonsPerPage
                    val call: Call<PokemonListResponse> = PokemonAPIClient.getPokemonData.getList(offset, pokemonsPerPage)

                    call.enqueue(object : Callback<PokemonListResponse> {
                        override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                            Log.d("API CALL", "Failed API CALL")
                        }

                        override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                            var response: PokemonListResponse = response!!.body()!!
                            pokemonAdapter!!.setList(response.pokemonList)
                            Log.d("API CALL", "Pokemon list size: " + response.pokemonList.size)
                        }
                    })
                }
            }
        }

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