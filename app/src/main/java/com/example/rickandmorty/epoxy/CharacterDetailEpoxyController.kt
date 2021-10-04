package com.example.rickandmorty.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterDetailDataPointBinding
import com.example.rickandmorty.databinding.ModelCharacterDetailHeaderBinding
import com.example.rickandmorty.databinding.ModelCharacterDetailImageBinding
import com.example.rickandmorty.model.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterDetailEpoxyController: EpoxyController() {

    private var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var characterResponse: GetCharacterByIdResponse? = null
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

        if (characterResponse == null) {
            // todo error state
            return
        }

        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)

        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
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
}