package com.lenakurasheva.gb_popular_libraries.mvp.model.repo

import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepo(val api: IDataSource) : IGithubUserReposRepo {

    override fun getUserRepos(url: String) = api.getUserRepos(url).subscribeOn(Schedulers.io())

}