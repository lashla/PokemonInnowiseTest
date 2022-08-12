package com.lasha.pokemoninnowisetest.ui.pokeList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lasha.pokemoninnowisetest.R
import com.lasha.pokemoninnowisetest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_poke_list.*


@AndroidEntryPoint
class PokeListFragment: Fragment(R.layout.fragment_poke_list) {

    private lateinit var viewModel: PokeListViewModel
    private var adapter = PokeRecyclerAdapter()
    private var offset = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        setupOnClickAndScrollListeners()
    }


    private fun initRecyclerView(){
        pokemonRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL
            , false)
        pokemonRecycler.itemAnimator = DefaultItemAnimator()
        pokemonRecycler.setHasFixedSize(true)
        pokemonRecycler.adapter = adapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]
        viewModel.characters.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    for (element in it.data!!){
                        adapter.updateRecycler(element)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progressBar.visibility = View.VISIBLE
            }
        }

    }
    private fun setupOnClickAndScrollListeners(){
        adapter.setOnItemClickListener {
            val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailsFragment(it.name)
            findNavController().navigate(action)
        }
        pokemonRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    offset += 20
                }
            }
        })
    }
}