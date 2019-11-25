package com.example.spek

class CustomerRepository {

    private var database = emptyList<Customer>()

    fun save(customer: Customer) {
        database = database.plus(customer)
    }

    fun findByAgeGreaterThan(threshold: Int): List<Customer> {
        return database.filter { it.age > threshold }
    }

    fun findFirstWithAge(age: Int): Customer? {
        return database.find { it.age == age }
    }

    fun clear() {
        database = emptyList()
    }
}
