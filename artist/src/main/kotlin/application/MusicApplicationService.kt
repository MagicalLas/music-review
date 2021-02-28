package application

import adapter.InMemoryArtistRepository
import adapter.InMemoryMusicRepository
import arrow.core.Either
import arrow.core.firstOrNone
import domain.ArtistNotFoundError
import domain.Music
import domain.MusicNotFoundError

class MusicApplicationService(
    private val artistRepository: InMemoryArtistRepository,
    private val musicRepository: InMemoryMusicRepository,
) {
    fun releaseNewMusic(artistId: String, title: String, description: String, sourceLink: String)
        : Either<ArtistNotFoundError, Music> {
        return artistRepository.findArtistById(artistId).map {
            Music(musicRepository.nextId(), title, description, sourceLink)
        }
    }

    fun findSpecificMusic(id: String): Either<MusicNotFoundError, Music> {
        return musicRepository.findAllMusic()
            .filter {
                it.id == id
            }.firstOrNone()
            .toEither {
                MusicNotFoundError(id)
            }
    }
}
