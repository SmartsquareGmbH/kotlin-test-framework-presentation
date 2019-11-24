package com.example.spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * Basic example for the specification style of Spek2.
 */
object CalculatorSpecificationSpek : Spek({

    describe("calculator") {
        val calculator = Calculator()

        describe("subtraction") {
            it("returns the correct result") {
                assertEquals(-3, calculator.subtract(2, 5))
            }
        }

        describe("addition") {
            it("returns the correct result") {
                assertEquals(7, calculator.add(2, 5))
            }

            describe("data driven") {
                class Calculation(val a: Int, val b: Int, val expected: Int)

                val data = listOf(
                    Calculation(1, 2, 3),
                    Calculation(-1, -2, -3),
                    Calculation(1, 2, 3),
                    Calculation(10000, 0, 10000)
                )

                data.forEach { calculation ->
                    it("returns ${calculation.expected} for ${calculation.a} + ${calculation.b}") {
                        assertEquals(calculation.expected, calculator.add(calculation.a, calculation.b))
                    }
                }
            }
        }
    }
})
