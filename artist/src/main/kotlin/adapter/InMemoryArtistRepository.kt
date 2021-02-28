package adapter

import arrow.core.Either
import arrow.core.rightIfNotNull
import domain.Artist
import domain.ArtistNotFoundError

class InMemoryArtistRepository {
    private val inMemoryCache: MutableMap<String, Artist> = mutableMapOf()
    private var lastId = 1

    fun save(artist: Artist) {
        inMemoryCache[artist.id] = artist
    }

    fun findAllArtists(): List<Artist> {
        return inMemoryCache.values.toList()
    }

    fun findArtistById(id: String): Either<ArtistNotFoundError, Artist> {
        return inMemoryCache[id].rightIfNotNull { ArtistNotFoundError(id) }
    }

    fun nextId(): String {
        return lastId++.toString()
    }
}
