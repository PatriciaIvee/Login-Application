package ph.kodego.leones.patricia.ivee.apis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ph.kodego.leones.patricia.ivee.apis.api.PokemonAPIClient
import ph.kodego.leones.patricia.ivee.apis.databinding.FragmentPokemonMovesBinding
import ph.kodego.leones.patricia.ivee.apis.model.PokemonInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonMovesFragment : Fragment() {

    var binding: FragmentPokemonMovesBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonMovesBinding.inflate(inflater, container, false)
        var view = binding!!.root


        return view
    }

    private val receiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var message: String? = intent!!.getStringExtra("data")
            Log.i("Pokemon Info", message!!.toString())
            message?.let{
                getData(message.getPokemonID())
            }
        }

    }

    private fun getData(id: Int){
        val call: Call<PokemonInfoResponse> = PokemonAPIClient.getPokemonData.getPokemonInfo(id)

        call.enqueue(object: Callback<PokemonInfoResponse> {
            override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable){
                Log.e("API Error", "Failed to get data from API", t)
            }

            override fun onResponse(
                call: Call<PokemonInfoResponse>,
                response: Response<PokemonInfoResponse>
            ){
                var response: PokemonInfoResponse = response!!.body()!!

                // Update the TextViews with the Pokemon information
                val movesStringBuilder = StringBuilder()
                for (move in response.moves) {
                    movesStringBuilder.append("- ${move.move.name}\n")
                }
                binding!!.movesList.text = movesStringBuilder.toString()

            }
        })
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

}