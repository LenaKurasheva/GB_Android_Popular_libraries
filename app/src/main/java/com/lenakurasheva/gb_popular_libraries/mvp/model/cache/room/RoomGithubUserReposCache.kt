package com.lenakurasheva.gb_popular_libraries.mvp.model.cache.room

import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUserReposCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.RoomGithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubUserReposCache(val db: Database): IGithubUserReposCache {

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable {
        return Completable.fromCallable {
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

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepository>> {
       return Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
            db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount, it.fullName, it.htmlUrl) }
        }
    }

}