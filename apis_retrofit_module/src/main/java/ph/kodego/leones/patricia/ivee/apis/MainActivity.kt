package ph.kodego.leones.patricia.ivee.apis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import ph.kodego.leones.patricia.ivee.apis.adapter.PokemonInfoAdapter
import ph.kodego.leones.patricia.ivee.apis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private val pokemonInfoFragment = PokemonInfoFragment()
    private val pokemonListFragment = PokemonListFragment()
    private val pokemonImageFragment = PokemonImageFragment()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var pokemonInfoAdapter:PokemonInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_holder,pokemonListFragment)
            .commit()

        pokemonInfoAdapter = PokemonInfoAdapter(supportFragmentManager)
        pokemonInfoAdapter.add(pokemonInfoFragment, "Pokemon Information")
        pokemonInfoAdapter.add(pokemonImageFragment, "Pokemon Image")
        binding.pokemonInfoViewpager.adapter = pokemonInfoAdapter
    }


}