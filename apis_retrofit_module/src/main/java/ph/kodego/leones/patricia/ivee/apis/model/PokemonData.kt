package ph.kodego.leones.patricia.ivee.apis.model

import com.google.gson.annotations.SerializedName

class Pokemon {
    @SerializedName("name")
    var name: String = ""
    @SerializedName("url")
    var url: String = ""

    constructor(name:String, url:String){
        this.name = name
        this.url = url
    }
}

class PokemonListResponse{
    
    @SerializedName("count")
    var count: Int = -1

    @SerializedName("next")
    var next: String = ""

    @SerializedName("previous")
    var previous: String = ""

    @SerializedName("results")
    var pokemonList: List<Pokemon> = ArrayList<Pokemon>()
}

class PokemonInfoResponse{
    @SerializedName("name")
    var name  = ""

    @SerializedName("height")
    var height = -1

    @SerializedName("id")
    var id = -1

    @SerializedName("abilities")
    var abilities: ArrayList<PokemonAbilityInfo> = ArrayList<PokemonAbilityInfo>()

    @SerializedName("moves")
    var moves:ArrayList<PokemonMovesInfo> = ArrayList<PokemonMovesInfo>()

    @SerializedName("sprites")
    var sprites:PokemonSprite = PokemonSprite()

}

class PokemonAbility{
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""
}

class PokemonAbilityInfo{
    @SerializedName("ability")
    var ability: PokemonAbility = PokemonAbility()

    @SerializedName("is_hidden")
    var isHidden = false

    @SerializedName("slot")
    var slot = -1
}

class PokemonSprite{
    @SerializedName("front_default")
    var front_default = ""

    @SerializedName("front_shiny")
    var front_shiny = ""
}

class PokemonMove {
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""

}

class PokemonMovesInfo {
    @SerializedName("move")
    var move : PokemonMove = PokemonMove()
}

