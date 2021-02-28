package application

import adapter.InMemoryArtistRepository
import arrow.core.Either
import domain.Music
import domain.MusicNotFoundError

class MusicApplicationService(private val artistRepository: InMemoryArtistRepository) {
    fun releaseNewMusic(id: String, title: String, description: String, sourceLink: String): Music {
        return Music(id)
    }

    fun findSpecificMusic(id: String): Either<MusicNotFoundError, Music> {
        return Either.right(Music(id))
    }
}