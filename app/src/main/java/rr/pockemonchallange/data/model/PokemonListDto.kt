package rr.pockemonchallange.data.model

data class PokemonListDto(
    val count: Long,
    val next: String,
    val previous: String,
    val results: Collection<PokemonSimpleDto>
)
