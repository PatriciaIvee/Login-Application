package ph.kodego.leones.patricia.ivee.apis.api

import ph.kodego.leones.patricia.ivee.apis.model.PokemonListResponse
import ph.kodego.leones.patricia.ivee.apis.model.PokemonInfoResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface PokemonAPI {
    @GET("pokemon/")
    fun getList(
        @Query("offset") startIndex:Int,
        @Query("limit") limit:Int)
            : Call<PokemonListResponse>


    @GET("pokemon/{pokemonID}/")
    fun getPokemonInfo(
        @Path("pokemonID") pokemonId: Int)
            : Call<PokemonInfoResponse>
}