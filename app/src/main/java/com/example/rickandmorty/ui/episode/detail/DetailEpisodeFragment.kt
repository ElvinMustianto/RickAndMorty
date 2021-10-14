package com.example.rickandmorty.ui.episode.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailEpisodeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailEpisodeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailEpisodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel:DetailEpisodeViewModel by viewModels()
    private val safeArgs: DetailEpisodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.episodeLiveData.observe(viewLifecycleOwner) { episode ->
            if (episode == null) {
                // todo handle error
                return@observe
            }
            binding.episodeName.text = episode.name
            binding.episodeDate.text = episode.airDate
            binding.episodeNumber.text = episode.getFormattedSeason()

            binding.epoxyRecyclerView.setControllerAndBuildModels(
                DetailEpisodeEpoxyController(episode.characters)
            )
        }
        viewModel.fetchEpisode(safeArgs.episodeId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}