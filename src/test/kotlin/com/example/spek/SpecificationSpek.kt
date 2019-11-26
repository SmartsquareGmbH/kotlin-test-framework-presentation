package com.example.spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * Basic example for the specification style of Spek2.
 *
 * Inspired by Jasmine and RSpec.
 */
object SpecificationSpek : Spek({

    describe("calculator") {
        val calculator = Calculator()

        context("subtraction") {
            it("returns the correct result") {
                assertEquals(-3, calculator.subtract(2, 5))
            }
        }

        describe("addition") {
            it("returns the correct result") {
                assertEquals(7, calculator.add(2, 5))
            }

            context("data driven") {
                data class Row(val a: Int, val b: Int, val expected: Int)

                val data = listOf(
                    Row(1, 2, 3),
                    Row(-1, -2, -3),
                    Row(1, 2, 3),
                    Row(10000, 0, 10000)
                )

                // There is no direct support for data driven tests, but we can use Kotlin's on board features,
                // to generate tests in place.
                data.forEach { calculation ->
                    it("returns ${calculation.expected} for ${calculation.a} + ${calculation.b}") {
                        assertEquals(calculation.expected, calculator.add(calculation.a, calculation.b))
                    }
                }
            }
        }
    }
})
