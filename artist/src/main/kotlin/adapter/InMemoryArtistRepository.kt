package adapter

import domain.Artist

class InMemoryArtistRepository {
    private val inMemoryCache: MutableMap<String, Artist> = mutableMapOf()

    fun save(artist: Artist) {
        inMemoryCache[artist.id] = artist
    }

    fun findAllArtists(): List<Artist> {
        return inMemoryCache.values.toList()
    }
}
