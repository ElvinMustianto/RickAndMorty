package com.example.rickandmorty.ui.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.rickandmorty.NavGraphDirections
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodeListBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class EpisodeListFragment : Fragment(R.layout.fragment_episode_list) {

    private var _binding: FragmentEpisodeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpisodeViewModel by viewModels()

    @ObsoleteCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeListBinding.bind(view)

        val epoxyController = EpisodeListEpoxyController{ episodeClickedId ->
            val direction = NavGraphDirections.actionGlobalToDetailEpisodeFragment(
                episodeId = episodeClickedId
            )
            findNavController().navigate(direction)
        }
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData: PagingData<EpisodeUiModel> ->
                epoxyController.submitData(pagingData)
            }
        }
        binding.epoxyRecyclerViewPage.setController(epoxyController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}