package com.example.rickandmorty.ui.character.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterSearchBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ObsoleteCoroutinesApi
class CharacterSearchFragment : Fragment(R.layout.fragment_character_search) {

    private var _binding: FragmentCharacterSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private var currentText = ""
    private val handler = Handler(Looper.getMainLooper())
    private val searchRunnable = Runnable {
        viewModel.submitQuery(currentText)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterSearchBinding.bind(view)
        val epoxyController = CharacterSearchEpoxyController { characterId ->
            val detail = CharacterSearchFragmentDirections.actionCharacterSearchFragmentToCharacterDetailFragment(
                characterId
            )
            findNavController().navigate(detail)
        }
        binding.epoxyRecyclerView.setControllerAndBuildModels(controller = epoxyController)

        binding.searchEditText.doAfterTextChanged {
            currentText = it?.toString() ?: ""

            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable, 500L)
        }
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                epoxyController.localException = null
                epoxyController.submitData(pagingData)
            }
        }
        viewModel.localExceptionEventLiveData.observe(viewLifecycleOwner) { event ->
            event.getContent()?.let { localException ->
                epoxyController.localException = localException
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}