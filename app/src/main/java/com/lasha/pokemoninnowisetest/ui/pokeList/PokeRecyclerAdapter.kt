package com.lasha.pokemoninnowisetest.ui.pokeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lasha.pokemoninnowisetest.R
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import kotlinx.android.synthetic.main.view_poke_recycler.view.*

class PokeRecyclerAdapter: RecyclerView.Adapter<PokeRecyclerAdapter.ViewHolder>() {

    private var pokemonList = ArrayList<Pokemon>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_poke_recycler, parent, false)
        return ViewHolder(view)
    }

    fun updateRecycler(newInfo: Pokemon){
        pokemonList.add(newInfo)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = pokemonList[position]
        holder.itemView.apply {
            pokemonNameTv.text = items.name
            setOnClickListener {
                onItemClickListener?.let {
                    it(items)
                }
            }
        }
    }

    override fun getItemCount(): Int =  pokemonList.size

    private var onItemClickListener: ((Pokemon) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }



}