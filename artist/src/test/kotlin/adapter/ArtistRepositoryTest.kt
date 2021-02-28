package adapter

import arrow.core.Either
import domain.Artist
import domain.ArtistNotFoundError
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.hasSize
import strikt.assertions.isTrue

class ArtistRepositoryTest : Spek({
    Feature("아티스트 저장하기") {
        val repository = InMemoryArtistRepository()

        Scenario("아티스트 저장하기") {
            lateinit var artist: Artist

            Given("올바른 아티스트가 주어진 경우") {
                artist = Artist("1", "Las", "Las is magical girl")
            }
            When("아티스트를 저장하는 경우") {
                repository.save(artist)
            }
            Then("아티스트를 찾을 수 있다.") {
                expectThat(repository.findAllArtists()).contains(artist)
            }
        }
    }
    Feature("아티스트 불러오기") {
        val repository = InMemoryArtistRepository()
        Scenario("특정한 아티스트만 불러오는 경우") {
            lateinit var artist: Artist
            lateinit var result: Either<ArtistNotFoundError, Artist>

            Given("아티스트가 저장이 되어있을 때") {
                artist = Artist(repository.nextId(), "Las", "Las is magical girl")
                repository.save(artist)
            }
            When("생성된 아이디로 아티스트를 불러오눈 경우") {
                result = repository.findArtistById(artist.id)
            }
            Then("아티스트를 불러올 수 있다.") {
                expectThat(result.isRight()).isTrue()
            }
        }
        Scenario("존재하지 않 아티스트만 불러오는 경우") {
            lateinit var result: Either<ArtistNotFoundError, Artist>

            Given("아티스트가 존재하지 않을 떄") {}
            When("생성된 아이디로 아티스트를 불러오눈 경우") {
                result = repository.findArtistById(repository.nextId())
            }
            Then("아티스트를 불러올 수 있다.") {
                expectThat(result.isLeft()).isTrue()
            }
        }

    }
    Feature("다음 식별자 생성하기") {
        val repository = InMemoryArtistRepository()

        Scenario("식별자 얻어오기") {
            lateinit var nextId: String

            Given("아티스트가 저장이 되어있을 때") {
                val artist = Artist(repository.nextId(), "Las", "Las is magical girl")
                repository.save(artist)
            }
            When("다음 식별자를 얻는 경우") {
                nextId = repository.nextId()
            }
            Then("얻은 식별자로 저장되어 있는 artist가 존재하지 않는다.") {
                expectThat(
                    repository.findAllArtists().filter {
                        it.id == nextId
                    }
                ).hasSize(0)
            }
        }
    }
})
