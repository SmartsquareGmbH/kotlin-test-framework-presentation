package com.example.junit

import com.github.zafarkhaja.semver.Version

class AppServer(private val iAppRepository: IAppRepository) {
    fun getAppUpdates(id: String, version: Version) =
        iAppRepository
            .getApp(id)
            ?.versions
            ?.filter {
                it > version
            }

    fun getSimilarApps(id: String) = iAppRepository.getSimilarApps(id)
}
