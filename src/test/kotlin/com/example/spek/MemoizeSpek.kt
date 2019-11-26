package com.example.spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode

/**
 * Example showing the memoize helper of Spek2.
 */
object MemoizeSpek : Spek({

    lateinit var list: List<Int>

    beforeEachTest {
        list = listOf(1, 3)
    }

    group("lateinit list") {
        test("should not be empty") {
            assertTrue(list.isNotEmpty())
        }

        test("size is 2") {
            assertEquals(2, list.size)
        }
    }

    /**
     * The same as above, but simpler. Use [CachingMode] to control, when the value is filled.
     */
    val memoizedList by memoized { mutableListOf(1, 3) }

    group("memoized list") {
        test("should not be empty") {
            assertTrue(memoizedList.isNotEmpty())
        }

        test("size is 2") {
            assertEquals(2, memoizedList.size)
        }
    }
})
