package rr.pockemonchallange.data.model

data class PokemonDto(
        val id: Int,
        val name: String,
        val baseExperience: Int,
        val height: Int,
        val isDefault: Boolean,
        val order: Int,
        val weight: Int,
//        val species: NamedApiResource,
//        val abilities: List<PokemonAbility>,
//        val forms: List<NamedApiResource>,
//        val gameIndices: List<VersionGameIndex>,
//        val heldItems: List<PokemonHeldItem>,
//        val moves: List<PokemonMove>,
//        val stats: List<PokemonStat>,
//        val types: List<PokemonType>,
        val sprites: PokemonSpritesDto
)