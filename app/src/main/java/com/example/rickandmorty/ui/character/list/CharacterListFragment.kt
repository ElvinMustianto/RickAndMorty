package com.example.rickandmorty.ui.character.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickandmorty.R

class CharacterListFragment : Fragment() {

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterPagedListLiveData.observe(viewLifecycleOwner) { pagedList ->
            epoxyController.submitList(pagedList)
        }
       view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setControllerAndBuildModels(epoxyController)
    }

    private fun onCharacterSelected(characterId: Int) {
      val direction = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
          characterId = characterId
      )
        findNavController().navigate(direction)
    }
}