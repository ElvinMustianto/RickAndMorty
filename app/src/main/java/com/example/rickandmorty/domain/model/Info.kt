package com.example.rickandmorty.domain.model

data class Info(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null
)
