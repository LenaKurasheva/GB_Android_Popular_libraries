package com.lenakurasheva.gb_popular_libraries.mvp.model.cache.room

import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.RoomGithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(val db: Database): IGithubUsersCache {

    override fun putUsers(users: List<GithubUser>): Completable {
        return Completable.fromAction() {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id ?: "",
                    user.login ?: "",
                    user.avatarUrl ?: "",
                    user.reposUrl ?: ""
                )
            }
            db.userDao.insert(roomUsers)
        }
    }

    override fun getUsers(): Single<List<GithubUser>> {
       return Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }
    }
}