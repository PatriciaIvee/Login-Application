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
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ph.kodego.leones.patricia.ivee.apis.databinding.FragmentPokemonImageBinding

class PokemonImageFragment : Fragment() {

    private val receiver  = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //filterAction
            var imageUrl : String? = intent!!.getStringExtra("data")
            Log.d("PokemonImageFragment", "Received image URL: $imageUrl")

            if (imageUrl != null) {
                Picasso.with(requireContext())
                    .load(imageUrl)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.cracked_egg)
                    .error(R.drawable.eggtwo)
                    .into(binding!!.pokemonImage, object : Callback {
                        override fun onSuccess() {
                            Log.d("PokemonImageFragment", "Successfully loaded image")
                        }

                        override fun onError() {
                            Log.e("PokemonImageFragment", "Error loading image")
                        }
                    })
            } else {
                Log.e("PokemonImageFragment", "No image URL found in intent")
            }

//            //if(imageUrl != null)
//            imageUrl?.let {
//                Picasso
//                    .with(activity!!.applicationContext)
//                    .load(it)
//                    .memoryPolicy(MemoryPolicy.NO_CACHE)
//                    .placeholder(R.drawable.cracked_egg)
//                    .error(R.drawable.eggtwo)
//                    .into(binding!!.pokemonImage)
//            }
        }

    }
    var binding: FragmentPokemonImageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentPokemonImageBinding.inflate(inflater, container,false)
        var view = binding!!.root
        return view
    }

    override fun onDestroyView() {
        requireActivity().unregisterReceiver(receiver)
        super.onDestroyView()
    }

    private fun setupReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.kodego.mdp2.LOADIMAGEACTION")
        requireActivity().registerReceiver(receiver,intentFilter)
    }


}
