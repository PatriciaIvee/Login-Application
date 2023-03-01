package ph.kodego.leones.patricia.ivee.apis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ph.kodego.leones.patricia.ivee.apis.databinding.FragmentPokemonImageBinding

class PokemonImageFragment : Fragment() {

    private val receiver  = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //filterAction
            var imageUrl : String? = intent!!.getStringExtra("data")

            //if(imageUrl != null)
            imageUrl?.let {
                Picasso
                    .with(activity!!.applicationContext)
                    .load(it)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.egg)
                    .error(R.drawable.egg)
                    .into(binding!!.pokemonImage)
            }
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
        intentFilter.addAction("ph.kodego.mdp2.broadcast.LOADIMAGEACTION")
        requireActivity().registerReceiver(receiver,intentFilter)
    }

}