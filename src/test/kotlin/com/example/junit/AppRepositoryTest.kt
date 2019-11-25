package com.example.junit

import org.amshove.kluent.`should be null`
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class AppRepositoryTest {
    @Test
    fun `Repository empty always returns null on find by id`() {
        val appRepository = AppRepository(emptyList())

        assertNull(appRepository.getApp("random!"))
        appRepository.getApp("cheese cake!").`should be null`()
    }
}
