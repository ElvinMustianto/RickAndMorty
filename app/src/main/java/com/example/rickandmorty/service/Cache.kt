package com.example.rickandmorty.service

import com.example.rickandmorty.domain.model.Character

object Cache {

     val characterMap = mutableMapOf<Int, Character>()
}