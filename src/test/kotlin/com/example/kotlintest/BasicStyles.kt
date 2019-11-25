package com.example.kotlintest

import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.specs.BehaviorSpec
import io.kotlintest.specs.DescribeSpec
import io.kotlintest.specs.ExpectSpec
import io.kotlintest.specs.FeatureSpec
import io.kotlintest.specs.FreeSpec
import io.kotlintest.specs.FunSpec
import io.kotlintest.specs.ShouldSpec
import io.kotlintest.specs.StringSpec
import io.kotlintest.specs.WordSpec

class MyFirstKotlinTest : StringSpec({
    // lambda param in constructor
    "string.length should return the length of the string" {
        "kotlintest".length shouldBe 10
        "".length shouldBe 0
    }

    "string.startsWith should check for a prefix" {
        "Kotlin is great".startsWith("Kotlin").shouldBeTrue()
    }
})

class MyFunSpec : FunSpec() {

    init { // inside init block
        test("string.length should return the length of the string") {
            "kotlintest".length shouldBe 10
        }

        context("you can group things within a context") {
            test("string.startsWith should check for a prefix") {
                "Kotlin is great".startsWith("Kotlin").shouldBeTrue()
            }
        }
    }
}

class MyShouldSpec : ShouldSpec({
    should("return the length of the string") {
        "kotlintest".length shouldBe 10
    }

    "String[] (get op)" {
        should("return char at position") {
            "kotlin"[2] shouldBe 't'
        }
        should("throw an IndexOutOfBounds exception when called outside of string range") {
            shouldThrow<IndexOutOfBoundsException> { "kotlin"[10] }
        }
    }
})

class MyWordSpec : WordSpec({
    "String.length" should {
        "return the length of the string" {
            "kotlintest".length shouldBe 10
        }
    }

    "String[] (get op)" When {
        "called with argument inside the range" should {
            "return char at position" {
                "kotlin"[2] shouldBe 't'
            }
        }
        "called with argument outside range" should {
            "throw an IndexOutOfBounds exception" {
                shouldThrow<IndexOutOfBoundsException> { "kotlin"[10] }
            }
        }
    }
})

class MyFeatureSpec : FeatureSpec({
    // inspired by cucumber

    feature("Kotlin provides convenient extension operators for String") {
        scenario("String[] (get operator) should be available") {
            "kotlin"[2] shouldBe 't'
        }
        scenario("String[] should throw IndexOutOfBoundsException") {
            shouldThrow<IndexOutOfBoundsException> { "kotlin"[10] }
        }
    }

})

class MyBehaviourSpec : BehaviorSpec({
    given("a String  'Kotlin'") {
        val string = "Kotlin"
        `when`("I get the length of it") {
            val length = string.length
            then("it should return 6") {
                length shouldBe 6
            }
        }
    }
})

class MyFreeSpec : FreeSpec({
    "String.length" - {
        "should return the length of the string" {
            "kotlintest".length shouldBe 10
        }
    }

    "String[] (get op)" - {
        "when called with" - {
            "an index inside the range" {
                "kotlin"[2] shouldBe 't'
            }
            "an index outside the range" {
                shouldThrow<IndexOutOfBoundsException> { "kotlin"[10] }
            }
        }
    }
})

class MyDescribeSpec : DescribeSpec({
    //mimics ruby rspec

    describe("String operators") {
        it("should provide a length property") {
            "kotlintest".length shouldBe 10
        }

        context("extension functions on String") {
            describe("String[] (get op)") {
                it("return a char at the requested position") {
                    "kotlin"[2] shouldBe 't'
                }
                it("throw an IndexOutOfBoundsException if requested position is out of range") {
                    shouldThrow<IndexOutOfBoundsException> { "kotlin"[10] }
                }
            }
        }
    }
})

class MyExpectSpec : ExpectSpec({
    context("String[] (get op)") {
        expect("char at requested position") {
            "kotlin"[2] shouldBe 't'
        }
        expect("IndexOutOfBoundsException if requested position is our of range") {
            shouldThrow<IndexOutOfBoundsException> { "kotlin"[10] }
        }
    }
})

class MyAnnotationSpec : AnnotationSpec() {

    @BeforeEach
    fun beforeTest() {
        println("Before each test")
    }

    @Test
    fun stringLengthShouldReturnLengthOfTheString() {
        "kotlintest".length shouldBe 10
    }

    @Test
    fun `string length should return the length of the string`() {
        "kotlintest".length shouldBe 10
    }
}
