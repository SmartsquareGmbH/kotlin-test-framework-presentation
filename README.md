# Kotlin test framework presentation

Source code for a presentation about testing in Kotlin.

### Frameworks

- [JUnit5](https://junit.org/junit5/)
  - [Kluent](https://github.com/MarkusAmshove/Kluent)
  - [Mockk](https://github.com/mockk/mockk)
- [Spek2](https://www.spekframework.org/)
- [KotlinTest](https://github.com/kotlintest/kotlintest)

### Structure

This is a Gradle project with each framework in it's own package (under `com.example`).
The Spek examples for example have the package `com.example.spek` in the 
`src/main/kotlin` (for class under test implementations) and 
`src/main/test` (for test implementations) folder. 
