package application

import adapter.InMemoryArtistRepository
import adapter.InMemoryMusicRepository
import domain.Artist
import domain.Music
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.isTrue

class MusicApplicationServiceTest: Spek({
    Feature("Release New Music") {
        val artistRepository = InMemoryArtistRepository()
        val musicRepository = InMemoryMusicRepository()
        val musicApplicationService = MusicApplicationService(
            artistRepository,
            musicRepository,
        )

        Scenario("한 아티스트가 자신이 만든 곡을 공개한다.") {
            lateinit var artist: Artist
            lateinit var releasedMusic: Music

            Given("아티스트가 이미 등록이 되어있으면") {
                artist = Artist(artistRepository.nextId(), "Wafia", "Wafia is artist")
                artistRepository.save(artist)
            }
            When("곡을 발표한다면") {
                val title = "Hurricane"
                val description = "I spent a day recently driving around LA delivering my friends and collaborators flowers and cakes I made then turned it into this little lyric video."
                val sourceLink = "https://www.youtube.com/watch?v=0XhbPmx4cnU"
                releasedMusic = musicApplicationService.releaseNewMusic(
                    artist.id,
                    title,
                    description,
                    sourceLink
                )
            }
            Then("공개한 곡을 찾을 수 있다.") {
                expectThat(musicApplicationService.findSpecificMusic(releasedMusic.id).isRight()).isTrue()
            }
        }
    }
})
