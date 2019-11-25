package com.example.junit

import com.github.zafarkhaja.semver.Version
import io.kotlintest.matchers.collections.shouldHaveSingleElement
import io.kotlintest.matchers.collections.shouldHaveSize
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AppServerTest {
    @Test
    fun `app with 1 version returns a single update`() {
        val iAppRepository = mockk<IAppRepository>()
        val expectedVersions = listOf(Version.forIntegers(1, 0, 0))
        val app = App("kuchen", expectedVersions)
        every { iAppRepository.getApp("kuchen") } returns app

        val appServer = AppServer(iAppRepository)

        val result = iAppRepository.getApp("kuchen")
        assertEquals(app, result)
        result `should be` app

        val updates = appServer.getAppUpdates("kuchen", Version.forIntegers(0))
        assertEquals(expectedVersions, updates)
        updates `should equal` expectedVersions
    }

    @Test
    fun `app with 2 versions returns a single update`(@MockK iAppRepository: IAppRepository) {
        val app = App(
            "kuchen", listOf(
                Version.forIntegers(1, 5, 0),
                Version.forIntegers(2, 0, 0)
            )
        )

        every { iAppRepository.getApp("kuchen") } returns app

        val appServer = AppServer(iAppRepository)

        val appUpdates = appServer.getAppUpdates("kuchen", Version.forIntegers(1, 7, 5))

        appUpdates.`should not be null`()
        val update = Version.forIntegers(2, 0, 0)

        // check size and content
        appUpdates shouldHaveSize 1
        appUpdates `should contain` update

        // single test
        appUpdates shouldHaveSingleElement update
    }
}
