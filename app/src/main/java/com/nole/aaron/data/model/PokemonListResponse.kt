package com.nole.aaron.data.model

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
)
