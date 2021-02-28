import org.opentest4j.AssertionFailedError
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

class TestSimple : Spek({
    Feature("Run Test") {
        Scenario("Assert two number is equal") {
            var number1: Int = 0
            var number2: Int = 0
            Given("숫자 1과 2가 주어진다면") {
                number1 = 1
                number2 = 2
            }
            When("두 숫자가 같음을 보장할 경우 테스트가 실패한다") {
                expectThrows<AssertionFailedError> {
                    expectThat(number1).isEqualTo(number2)
                }
            }
        }

        Scenario("Assert same number is equal") {
            var number1: Int = 0
            var number2: Int = 0
            Given("숫자 1과 1이 주어진다면") {
                number1 = 1
                number2 = 1
            }
            When("두 숫자가 같음을 보장할 경우 테스트가 성공한다") {
                expectThat(number1).isEqualTo(number2)
            }
        }
    }
})