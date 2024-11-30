package com.nole.aaron.data.database.network
import com.nole.aaron.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonListResponse>
}