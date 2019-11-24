package com.example.spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Basic example for the gherkin style of Spek2.
 */
object CalculatorGherkinSpek : Spek({

    Feature("calculator") {
        val calculator = Calculator()

        Scenario("addition") {
            var result = 0

            When("calculating 2 + 5") {
                result = calculator.add(2, 5)
            }

            Then("the result is 7") {
                assertEquals(result, 7)
            }
        }
    }
})
