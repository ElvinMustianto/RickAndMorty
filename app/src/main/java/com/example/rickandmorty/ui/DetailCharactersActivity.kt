package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickandmorty.INTENT_EXTRA_CHARACTER_ID
import com.example.rickandmorty.R
import com.example.rickandmorty.character.detail.DetailViewModel
import com.example.rickandmorty.epoxy.CharacterDetailEpoxyController

class DetailCharactersActivity : AppCompatActivity() {

    private val epoxyController = CharacterDetailEpoxyController()

   private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_characters)

        viewModel.characterByIdLiveData.observe(this) { character ->

            epoxyController.character = character

            if (character == null) {
                Toast.makeText(
                    this@DetailCharactersActivity,
                    "Unsuccessful network call",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }
        val id = intent.getIntExtra(INTENT_EXTRA_CHARACTER_ID, 1)
        viewModel.refreshCharacter(id)

       val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}