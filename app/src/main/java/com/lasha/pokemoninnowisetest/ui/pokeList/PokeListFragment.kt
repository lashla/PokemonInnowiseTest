package com.lasha.pokemoninnowisetest.ui.pokeList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lasha.pokemoninnowisetest.R
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_poke_list.*

private const val limit = 20

@AndroidEntryPoint
class PokeListFragment: Fragment(R.layout.fragment_poke_list) {

    var data = ArrayList<Pokemon>()
    private val viewModel: PokeListViewModel by viewModels()
    private var adapter = PokeRecyclerAdapter(data)
    private var offset = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        initRecyclerView()
        setupOnClickAndScrollListeners()
    }

    private fun initRecyclerView(){
        pokemonRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,
            false)
        pokemonRecycler.itemAnimator = DefaultItemAnimator()
        pokemonRecycler.adapter = adapter
    }

    private fun initViewModel(){
        viewModel.charactersData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                for (element in it){
                    data.add(element)
                    element.id?.let { id -> adapter.notifyItemInserted(id) }
                }
            }
        }
    }

    private fun setupOnClickAndScrollListeners(){
        adapter.setOnItemClickListener {
            val action = it.name?.let { item ->
                PokeListFragmentDirections.actionPokeListFragmentToPokeDetailsFragment(
                    item
                )
            }
            if (action != null) {
                findNavController().navigate(action)
            }
        }
        pokemonRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    offset += 20
                    viewModel.retrieveData(offset, limit)
                }
            }
        })
    }
}