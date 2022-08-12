package com.lasha.pokemoninnowisetest.ui.pokeDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.lasha.pokemoninnowisetest.R
import com.lasha.pokemoninnowisetest.data.entities.Pokemon
import com.lasha.pokemoninnowisetest.utils.Resource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_poke_details.*

@AndroidEntryPoint
class PokeDetailsFragment: Fragment(R.layout.fragment_poke_details) {

    private lateinit var viewModel: PokeDetailViewModel
    private val navArgs by navArgs<PokeDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[PokeDetailViewModel::class.java]
        viewModel.start(navArgs.id)
        viewModel.character.observe(viewLifecycleOwner){
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    setupCharacterView(it.data!!)
                    progressBarDetails.visibility = View.GONE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    progressBarDetails.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupCharacterView(character: Pokemon) {
        pokeNameTv.text = character.name
        typeTv.text = character.type
        val height = "height" + character.height.toString()
        heightTv.text = height
        val weight = "weight" + character.weight.toString()
        weightTv.text = weight
        Picasso.get()
            .load(character.image)
            .resize(500,500)
            .error(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
            .into(imageView)
    }
}