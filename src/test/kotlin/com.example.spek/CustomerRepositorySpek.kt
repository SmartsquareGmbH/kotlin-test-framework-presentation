package com.example.spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip

/**
 * Example showing grouping and fixtures of Spek2.
 */
object CustomerRepositorySpek : Spek({

    val repository = CustomerRepository()

    beforeEachTest {
        repository.save(Customer(1, "Paul", 20))
        repository.save(Customer(1, "Rolf", 45))
        repository.save(Customer(1, "Maike", 27))
    }

    group("find by age greater than 26") {
        test("size is 2") {
            assertEquals(2, repository.findByAgeGreaterThan(26).size)
        }

        test ("first is Rolf") {
            assertEquals("Rolf", repository.findByAgeGreaterThan(26).first().name)
        }
    }

    group("find first with age") {
        group("0") {
            test("is null") {
                assertNull(repository.findFirstWithAge(0))
            }
        }

        group("27") {
            test("is not null") {
                assertNotNull(repository.findFirstWithAge(27))
            }

            test("is Maike") {
                assertEquals("Maike", repository.findFirstWithAge(27)!!.name)
            }
        }
    }

    // Question: Why do these tests fail?
    group("find by age greater than 26", skip = Skip.Yes("demonstration")) {
        val result = repository.findByAgeGreaterThan(26)

        test("size is 2") {
            assertEquals(2, result.size)
        }

        test ("first is Rolf") {
            assertEquals("Rolf", result.first().name)
        }
    }

    afterEachTest {
        repository.clear()
    }
})
