package com.lenakurasheva.gb_popular_libraries.mvp.model.repo

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import java.lang.RuntimeException

class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6")
    )

    fun getUsersFromNet(): List<GithubUser>? {
        Thread.sleep(1500)
        return repositories
    }

    fun getUsers() = Observable.create <List<GithubUser>?> { emitter ->
        try {
            val result = getUsersFromNet()
            if (result != null && result is List<GithubUser>?) {
                emitter.onNext(result)
                emitter.onComplete()
            } else {
                emitter.onError(RuntimeException("Failure"))
            }
        } catch (t: Throwable) {
            emitter.onError(RuntimeException("getUsersFromNet() failure"))
        }
    }
}