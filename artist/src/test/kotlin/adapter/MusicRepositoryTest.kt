package adapter

import domain.Music
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.hasSize

class MusicRepositoryTest : Spek({
    Feature("음악 저장하기") {
        val repository = InMemoryMusicRepository()

        Scenario("음악 저장하기") {
            lateinit var music: Music

            Given("올바른 음악이 주어진 경우") {
                val title = "Hurricane"
                val description = "I spent a day recently driving around LA delivering my friends and collaborators flowers and cakes I made then turned it into this little lyric video."
                val sourceLink = "https://www.youtube.com/watch?v=0XhbPmx4cnU"
                music = Music("1", title, description, sourceLink)
            }
            When("음악을 저장할 때") {
                repository.save(music)
            }
            Then("음악를 찾을 수 있다.") {
                expectThat(repository.findAllMusic()).contains(music)
            }
        }
    }
    Feature("다음 식별자 생성하기") {
        val repository = InMemoryMusicRepository()

        Scenario("식별자 얻어오기") {
            lateinit var nextId: String

            Given("음악이 저장 되어있을 때") {
                val title = "Hurricane"
                val description = "I spent a day recently driving around LA delivering my friends and collaborators flowers and cakes I made then turned it into this little lyric video."
                val sourceLink = "https://www.youtube.com/watch?v=0XhbPmx4cnU"
                val music = Music(repository.nextId(), title, description, sourceLink)
                repository.save(music)
            }
            When("다음 식별자를 얻는 경우") {
                nextId = repository.nextId()
            }
            Then("얻은 식별자로 저장되어 있는 음악이 존재하지 않는다.") {
                expectThat(
                    repository.findAllMusic().filter {
                        it.id == nextId
                    }
                ).hasSize(0)
            }
        }
    }
})
