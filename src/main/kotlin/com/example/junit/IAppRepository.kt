package com.example.junit

interface IAppRepository {
    fun getApp(id: String): App?
    fun getSimilarApps(id: String): List<App>
}
