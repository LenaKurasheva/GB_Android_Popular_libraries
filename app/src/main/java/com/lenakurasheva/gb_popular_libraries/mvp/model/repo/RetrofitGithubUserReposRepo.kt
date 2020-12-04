package com.lenakurasheva.gb_popular_libraries.mvp.model.repo

import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.RoomGithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) : IGithubUserReposRepo {

    override fun getUserRepos(user: GithubUser) = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getUserRepos(url).flatMap { repositories ->
                    Single.fromCallable {
                        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
                        val roomRepos = repositories.map {
                            RoomGithubRepository(
                                it.id ?: "",
                                it.name ?: "",
                                it.forksCount ?: "",
                                it.fullName ?: "",
                                it.description ?: "",
                                it.htmlUrl ?: "",
                                roomUser.id
                            )
                        }
                        db.repositoryDao.insert(roomRepos)
                        repositories
                    }
                }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())

        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount, it.fullName, it.htmlUrl) }
            }
        }
    }.subscribeOn(Schedulers.io())
}