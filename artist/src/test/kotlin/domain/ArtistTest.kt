package domain

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class ArtistTest: Spek({
    Feature("아티스트 비교하기") {
        Scenario("같은 아티스트가 같은 아티스트인지 비교한다.") {
            lateinit var artist1: Artist
            lateinit var artist2: Artist
            var result = false

            Given("id가 같은 아티스트 2개가 주어진다면") {
                artist1 = Artist("1", "Las", "Las is magical girl")
                artist2 = Artist("1", "Wonho", "Wonho is magical girl")
            }
            When("아티스트가 같은지 비교하는 경우") {
                result = artist1 == artist2
            }
            Then("그 둘은 같은 아티스트이다.") {
                expectThat(result).isTrue()
            }
        }

        Scenario("다른 아티스트가 같은 아티스트인지 비교한다.") {
            lateinit var artist1: Artist
            lateinit var artist2: Artist
            var result = false

            Given("id가 같은 아티스트 2개가 주어진다면") {
                artist1 = Artist("1", "Las", "Las is magical girl")
                artist2 = Artist("2", "Wonho", "Wonho is magical girl")
            }
            When("아티스트가 같은지 비교하는 경우") {
                result = artist1 == artist2
            }
            Then("그 둘은 같은 아티스트이다.") {
                expectThat(result).isFalse()
            }
        }
    }
})