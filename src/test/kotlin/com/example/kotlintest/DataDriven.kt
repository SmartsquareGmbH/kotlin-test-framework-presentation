package com.example.kotlintest

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class MyDataDrivenTest : StringSpec({

    "Math.max of two numbers should return the maximum" {
        forall(
            row(1, 3, 3),
            row(8, 7, 8),
            row(0, 0, 0)
        ) { a, b, max ->
            Math.max(a, b) shouldBe max
        }
    }

    listOf(
        row(1, 3, 3),
        row(8, 7, 8),
        row(0, 0, 0)
    ).map { (a, b, max) ->
        "Math.max of $a and $b should return $max" {
            Math.max(a, b) shouldBe max
        }
    }

})


