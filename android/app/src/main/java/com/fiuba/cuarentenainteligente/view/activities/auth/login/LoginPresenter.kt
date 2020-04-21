package com.fiuba.cuarentenainteligente.view.activities.auth.login

import com.fiuba.cuarentenainteligente.interactor.loginInteractor.LoginInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginPresenter(loginInteractor: LoginInteractor) : LoginContract.LoginPresenter, CoroutineScope {

    var view:LoginContract.LoginView? = null
    var loginInteractor:LoginInteractor?=null
    private val job = Job()

    init {
        this.loginInteractor = loginInteractor
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
       return view != null
    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        launch {
            view?.showProgressBar()

            try{
                loginInteractor?.signInWithEmailAndPassword(email, password)

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }

            } catch (e:FirebaseLoginException){

                if(isViewAttached()){
                    view?.showError(e.message)
                    view?.hideProgressBar()
                }

            }
        }

    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()
    }



}