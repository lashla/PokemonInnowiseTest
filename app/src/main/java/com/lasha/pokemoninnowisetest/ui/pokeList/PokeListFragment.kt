package com.lasha.pokemoninnowisetest.ui.pokeList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lasha.pokemoninnowisetest.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_poke_list.*

@AndroidEntryPoint
class PokeListFragment: Fragment(R.layout.fragment_poke_list) {
    private var adapter = PokeRecyclerAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        onCharacterClick()
        for (i  in 0..10){
            adapter.updateRecycler("POKEEE")
        }
    }

    private fun initRecyclerView(){
        pokemonRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL
            , false)
        pokemonRecycler.itemAnimator = DefaultItemAnimator()
        pokemonRecycler.adapter = adapter
    }
    private fun onCharacterClick(){
        adapter.setOnItemClickListener {
            val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
}