package adapter

import domain.Artist

class InMemoryArtistRepository {
    private val inMemoryCache: MutableMap<String, Artist> = mutableMapOf()
    private var lastId = 1

    fun save(artist: Artist) {
        inMemoryCache[artist.id] = artist
    }

    fun findAllArtists(): List<Artist> {
        return inMemoryCache.values.toList()
    }

    fun nextId(): String {
        return lastId++.toString()
    }
}
