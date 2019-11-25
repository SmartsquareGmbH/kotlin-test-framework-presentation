package com.example.spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek

/**
 * Basic example for the default style of Spek2.
 */
object CalculatorSpek : Spek({

    group("calculator") {
        val calculator = Calculator()

        group("subtraction") {
            test("returns the correct result") {
                assertEquals(-3, calculator.subtract(2, 5))
            }
        }

        group("addition") {
            test("returns the correct result") {
                assertEquals(7, calculator.add(2, 5))
            }

            group("data driven") {
                class Calculation(val a: Int, val b: Int, val expected: Int)

                val data = listOf(
                    Calculation(1, 2, 3),
                    Calculation(-1, -2, -3),
                    Calculation(1, 2, 3),
                    Calculation(10000, 0, 10000)
                )

                data.forEach { calculation ->
                    test("returns ${calculation.expected} for ${calculation.a} + ${calculation.b}") {
                        assertEquals(calculation.expected, calculator.add(calculation.a, calculation.b))
                    }
                }
            }
        }
    }
})
