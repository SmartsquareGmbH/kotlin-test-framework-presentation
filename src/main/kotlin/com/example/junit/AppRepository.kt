package com.example.junit

class AppRepository(private val apps: List<App>) : IAppRepository {
    override fun getApp(id: String) =
        apps.firstOrNull { it.id == id }

    override fun getSimilarApps(id: String) =
        apps.filter { it.id.contains(id) }
}
