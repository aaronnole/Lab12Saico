package com.nole.aaron

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
)
