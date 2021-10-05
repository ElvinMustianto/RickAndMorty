package com.example.rickandmorty.epoxy

import android.annotation.SuppressLint
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.*
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Episode
import com.squareup.picasso.Picasso

class CharacterDetailEpoxyController: EpoxyController() {

    private var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null) {
            // todo error state
            return
        }
        // Header Model
        HeaderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)
        // Image Model
        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("image").addTo(this)

        // Episode carousel list section
        if (character!!.episodeList.isNotEmpty()) {
            val item = character!!.episodeList.map {
                EpisodeItem(it).id(it.id)
            }
            Title(title = "Episode").id("title_episodes").addTo(this)
            CarouselModel_()
                .id("episode")
                .models(item)
                .numViewsToShowOnScreen(1.1f)
                .addTo(this)
        }
        // Data point models
        DataPointEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("data_point_2").addTo(this)
    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailHeaderBinding>(R.layout.model_character_detail_header) {

        override fun ModelCharacterDetailHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            if (gender.equals("male", true)) {
                genderImageView.setImageResource(R.drawable.ic_male)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female)
            }
        }
    }

    data class ImageEpoxyModel(val imageUrl: String
    ): ViewBindingKotlinModel<ModelCharacterDetailImageBinding>(R.layout.model_character_detail_image) {

        override fun ModelCharacterDetailImageBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailDataPointBinding>(R.layout.model_character_detail_data_point) {

        override fun ModelCharacterDetailDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }

    data class EpisodeItem(
        val episode: Episode
    ): ViewBindingKotlinModel<ModelEpisodeItemBinding>(R.layout.model_episode_item) {
        @SuppressLint("SetTextI18n")
        override fun ModelEpisodeItemBinding.bind() {
            episodeText.text = episode.episode
            episodeDetailText.text = "${episode.name}\n${episode.airDate}"
        }
    }

    data class Title(
        val title: String
    ): ViewBindingKotlinModel<ModelTitleBinding>(R.layout.model_title) {
        override fun ModelTitleBinding.bind() {
            titleText.text = title
        }
    }

}