import arrow.core.Either
import domain.Artist
import domain.ArtistNotFoundError

class ArtistApplicationService {
    fun  enrollArtist(name: String, description: String): Artist {
        return Artist("")
    }

    fun findSpecificArtist(id: String): Either<ArtistNotFoundError, Artist> {
        return Either.right(Artist(""))
    }
}
