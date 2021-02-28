package application

import ArtistApplicationService
import domain.Artist
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue

class ArtistApplicationServiceTest : Spek({
    Feature("Artist 등록하기") {
        val artistApplicationService = ArtistApplicationService()
        Scenario("새로운 아티스트 등록하기") {
            lateinit var name: String
            lateinit var description: String
            lateinit var enrolledArtist: Artist

            Given("Artist 에 필요한 정보가 전부 제공되었다면") {
                name = "sayuri"
                description = "sayuri는 일본의 가수이다. Mikazuki라는 노래로 데뷔하게 되었다."
            }
            When("Artist 를 등록하는 경우") {
                enrolledArtist = artistApplicationService.enrollArtist(name, description)
            }
            Then("Artist를 찾을 수 있다") {
                val foundArtist = artistApplicationService.findSpecificArtist(enrolledArtist.id)

                expectThat(foundArtist.isRight()).isTrue()
                foundArtist.map {
                    expectThat(it).isEqualTo(enrolledArtist)
                }
            }
        }
    }
})
