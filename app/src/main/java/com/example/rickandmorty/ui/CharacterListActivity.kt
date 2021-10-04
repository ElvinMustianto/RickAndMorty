package com.example.rickandmorty.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickandmorty.INTENT_EXTRA_CHARACTER_ID
import com.example.rickandmorty.R
import com.example.rickandmorty.character.list.CharacterViewModel
import com.example.rickandmorty.epoxy.CharacterListPagingEpoxyController

class CharacterListActivity : AppCompatActivity() {

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list_activity)

        viewModel.characterPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }
        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setControllerAndBuildModels(epoxyController)
    }

   private fun onCharacterSelected(characterId: Int) {
       val intent = Intent(this, DetailCharactersActivity::class.java)
       intent.putExtra(INTENT_EXTRA_CHARACTER_ID, characterId)
       startActivity(intent)
   }
}