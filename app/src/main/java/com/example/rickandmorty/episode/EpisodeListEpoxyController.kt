package com.example.rickandmorty.episode

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelEpisodeListItemBinding
import com.example.rickandmorty.databinding.ModelHeaderTiltelBinding
import com.example.rickandmorty.domain.model.Episodes
import com.example.rickandmorty.epoxy.ViewBindingKotlinModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class EpisodeListEpoxyController: PagingDataEpoxyController<EpisodeUiModel>() {

    override fun buildItemModel(currentPosition: Int, item: EpisodeUiModel?): EpoxyModel<*> {
       return when (item!!) {
           is EpisodeUiModel.Item -> {
               val episode = (item as EpisodeUiModel.Item).episode
               EpisodeListItemEpoxyModel(
                   episodes = episode,
                   onClick = { episodeId ->
                       // todo
                   }
               ).id("episode_${episode.id}")
           }
           is EpisodeUiModel.Header -> {
               val headerText = (item as EpisodeUiModel.Header).text
               EpisodeListTitleEpoxyModel(headerText).id("header_${headerText}")
           }
       }
    }

    data class EpisodeListItemEpoxyModel(
        val episodes: Episodes,
        val onClick: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item) {
        override fun ModelEpisodeListItemBinding.bind() {
            episodeNameText.text = episodes.name
            episodeAirDateText.text = episodes.airDate
            episodeNumberText.text = episodes.getFormattedSeasonTruncated()

            root.setOnClickListener { onClick(episodes.id) }
        }
    }

    data class EpisodeListTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelHeaderTiltelBinding>(R.layout.model_header_tiltel) {
        override fun ModelHeaderTiltelBinding.bind() {
            textView.text = title
        }
    }

}