package com.example.junit

import io.mockk.junit5.MockKExtension
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@ExtendWith(MockKExtension::class)
class AppServerDataTest {
    companion object {
        @JvmStatic
        fun getApps(): List<Triple<List<App>, String, Int>> {
            return listOf(
                Triple(emptyList(), "", 0)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("getApps")
    fun `finding apps by substring`(args: Triple<List<App>, String, Int>) {
        val (apps: List<App>, search: String, expectedResults: Int) = args

        val appServer = AppServer(AppRepository(apps))
        val results = appServer.getSimilarApps(search)

        results shouldHaveSize expectedResults
    }

    @Nested
    inner class Provider {
        @ParameterizedTest
        @ArgumentsSource(AppsProvider::class)
        fun `finding apps by substring`(args: Triple<List<App>, String, Int>) {
            val (apps: List<App>, search: String, expectedResults: Int) = args

            val appServer = AppServer(AppRepository(apps))
            val results = appServer.getSimilarApps(search)

            results shouldHaveSize expectedResults
        }
    }

    class AppsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<Arguments> = Stream.of(
            Triple(
                listOf(
                    App("cheese cake", emptyList()),
                    App("chocolate cake", emptyList())
                ), "cake", 2
            )
        )
            .map { Arguments.of(it) }

    }
}
