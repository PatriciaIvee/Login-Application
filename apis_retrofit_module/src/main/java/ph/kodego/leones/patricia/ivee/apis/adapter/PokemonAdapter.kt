package ph.kodego.leones.patricia.ivee.apis.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.kodego.leones.patricia.ivee.apis.databinding.ItemPokemonListRowBinding
import ph.kodego.leones.patricia.ivee.apis.model.Pokemon

class PokemonAdapter (private val context: Context,
                      private var pokemonlist: ArrayList<Pokemon>)
    : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

        override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder{
            val itemBinding =
                ItemPokemonListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(itemBinding)
        }

        override fun getItemCount() = pokemonlist.size

        override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
            holder.bindItems(pokemonlist[position])
            holder.btn_view_data.setOnClickListener {
                Log.d("Pokemon", "${pokemonlist[position].name}")

                Intent().also{
                    Log.d("Pokemon URL", "${pokemonlist[position].url}")
                    it.action = "ph.kodego.mdp2.GETDATA"
                    it.putExtra("data", pokemonlist[position].url)
                    context.sendBroadcast(it)
                }
            }
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {}

        fun setList(pokemonlist: List<Pokemon>){
            this.pokemonlist.clear()
            this.pokemonlist.addAll(pokemonlist)
            notifyDataSetChanged()
        }

        class ViewHolder(private val itemBinding: ItemPokemonListRowBinding)
            : RecyclerView.ViewHolder(itemBinding.root){
                    public var btn_view_data = itemBinding.btnViewData

                    fun bindItems(pokemon: Pokemon){
                        itemBinding.pokemonName.text = pokemon.name
                    }
                }

    }
