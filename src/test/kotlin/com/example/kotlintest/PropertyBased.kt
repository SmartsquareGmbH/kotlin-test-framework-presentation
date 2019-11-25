package com.example.kotlintest

import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.properties.Gen
import io.kotlintest.properties.assertAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MyPropertyTest : StringSpec({

    "String size" {
        assertAll { a: String, b: String ->
            //println("gen: \na: '$a' \nb: '$b'")
            (a + b).length shouldBe (a.length + b.length)
        }
    }

    "String size explicit generators" {
        assertAll(Gen.string(), Gen.string()) { a: String, b: String ->
            //println("gen: \na: '$a' \nb: '$b'")
            (a + b).length shouldBe (a.length + b.length)
        }
    }

})

data class Person(val name: String, val age: Int)

class PersonGenerator : Gen<Person> {

    override fun constants(): Iterable<Person> = listOf(Person("Alfred E. Neumann", 20))

    override fun random(): Sequence<Person> = generateSequence {
        Person(Gen.string().random().first(), Gen.int().random().first())
    }

}

class MyPropertyPersonTest : StringSpec() {

    init  {
        "A Person should not have a negative age".config(enabled = false)  {
            assertAll(PersonGenerator()) { p: Person ->
                println("Person tested: $p")
                p.age shouldBeGreaterThan 0
            }
        }
    }
}

