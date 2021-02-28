package application

import adapter.InMemoryArtistRepository
import arrow.core.Either
import arrow.core.firstOrNone
import domain.Artist
import domain.ArtistNotFoundError


class ArtistApplicationService(
    private val artistRepository: InMemoryArtistRepository
) {

    fun enrollArtist(name: String, description: String): Artist {
        val artist = Artist("", name, description)
        artistRepository.save(artist)
        return artist
    }

    fun findSpecificArtist(id: String): Either<ArtistNotFoundError, Artist> {
        return artistRepository.findAllArtists()
            .filter {
                it.id == id
            }.firstOrNone()
            .toEither {
                ArtistNotFoundError(id)
            }
    }
}
