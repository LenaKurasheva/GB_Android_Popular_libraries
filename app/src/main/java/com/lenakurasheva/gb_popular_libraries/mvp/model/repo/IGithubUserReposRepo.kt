package com.lenakurasheva.gb_popular_libraries.mvp.model.repo

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single

interface IGithubUserReposRepo {
    fun getUserRepos(url: String): Single<List<GithubRepository>>
}