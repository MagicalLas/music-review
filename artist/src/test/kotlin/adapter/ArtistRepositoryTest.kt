package adapter

import domain.Artist
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.contains

class ArtistRepositoryTest: Spek({
    Feature("아티스트 저장하기") {
        val repository = InMemoryArtistRepository()

        Scenario("아티스트 저장하기") {
            lateinit var artist: Artist

            Given("올바른 아티스트가 주어진 경우"){
                artist = Artist("1", "Las", "Las is magical girl")
            }
            When("아티스트를 저장하는 경우"){
                repository.save(artist)
            }
            Then("아티스트를 찾을 수 있다."){
                expectThat(repository.findAllArtists()).contains(artist)
            }
        }
    }
})
