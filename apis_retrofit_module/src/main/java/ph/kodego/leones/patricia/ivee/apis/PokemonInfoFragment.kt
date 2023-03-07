package ph.kodego.leones.patricia.ivee.apis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.kodego.leones.patricia.ivee.apis.api.PokemonAPIClient
import ph.kodego.leones.patricia.ivee.apis.databinding.FragmentPokemonInfoBinding
import ph.kodego.leones.patricia.ivee.apis.model.PokemonInfoResponse
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class PokemonInfoFragment : Fragment() {

    private val receiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
           var message: String? = intent!!.getStringExtra("data")
            Log.i("Pokemon Info", message!!.toString())
            message?.let{
                getData(message.getPokemonID())
            }
        }

    }
    var binding: FragmentPokemonInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
        var view = binding!!.root
        return view
    }

    override fun onDestroy() {
        requireActivity().unregisterReceiver(receiver)
        super.onDestroy()
    }

    private fun setupReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.kodego.mdp2.GETDATA")
        requireActivity().registerReceiver(receiver, intentFilter)
    }

    private fun getData(id: Int){
        val call: Call<PokemonInfoResponse> = PokemonAPIClient.getPokemonData.getPokemonInfo(id)

        call.enqueue(object: Callback<PokemonInfoResponse>{
            override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable){
                Log.e("API Error", "Failed to get data from API", t)
            }

            override fun onResponse(
                call: Call<PokemonInfoResponse>,
                response: Response<PokemonInfoResponse>
            ){
                var response:PokemonInfoResponse = response!!.body()!!

                // Update the TextViews with the Pokemon information
                binding!!.pokemonId.text = "Pokemon ID: \n${response.id}"
                binding!!.pokemonName.text = "Pokemon Name: \n${response.name}"
                binding!!.pokemonHeight.text = "Pokemon Height: \n${response.height}"
                binding!!.pokemonAbilities.text = "Pokemon Abilities: \n${response.abilities.joinToString(separator = ", ") { it.ability.name }}"


                Intent().also{
                    Log.d("Pokemon", "${response.sprites.front_default}")
                    it.setAction("ph.kodego.mdp2.LOADIMAGEACTION")
                    it.putExtra("default_image", response.sprites.front_default)
                    it.putExtra("shiny_image", response.sprites.front_shiny)
                    context!!.sendBroadcast(it)
                }
                Log.d("API INFO CALL", response.name)
            }
        })
    }


}