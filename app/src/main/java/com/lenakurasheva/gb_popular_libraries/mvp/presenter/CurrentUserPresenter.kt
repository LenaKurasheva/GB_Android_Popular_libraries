package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.di.repository.IRepositoryScopeContainer
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.IGithubUserReposRepo
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.list.IUserReposListPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.CurrentUserView
import com.lenakurasheva.gb_popular_libraries.mvp.view.list.RepoItemView
import com.lenakurasheva.gb_popular_libraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CurrentUserPresenter (val user: GithubUser) : MvpPresenter<CurrentUserView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var userReposRepo: IGithubUserReposRepo
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var scope: IRepositoryScopeContainer


    class UserReposListPresenter : IUserReposListPresenter {
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        val repos = mutableListOf<GithubRepository>()

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
            repo.description?.let {view.setDescription(it)}
        }
        override fun getCount() = repos.size
    }

    val userReposListPresenter = UserReposListPresenter()

    var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userReposListPresenter.itemClickListener = { view ->
            val repository = userReposListPresenter.repos[view.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    fun onResume(){
        viewState.setLoginToToolbar(userLogin = user.login)
    }

    fun loadData() {
        userReposRepo.getUserRepos(user)
                .retry(3)
                .observeOn(uiScheduler)
                .subscribe(
                    {
                        userReposListPresenter.repos.clear()
                        userReposListPresenter.repos.addAll(it)
                        viewState.updateUsersList()
                    },
                    { println("onError: ${it.message}") })

        }


    fun backClick(): Boolean {
        viewState.removeLoginFromToolbar()
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
        scope.releaseRepositoryScope()
    }

}