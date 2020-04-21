package com.fiuba.cuarentenainteligente.view.activities.auth.signup

import androidx.core.util.PatternsCompat
import com.fiuba.cuarentenainteligente.interactor.signUpInteractor.SignUpInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SignUpPresenter(signUpInteractor: SignUpInteractor):SignUpContract.SignUpPresenter, CoroutineScope {

    var view:SignUpContract.SignUpView?= null
    var signUpInteractor:SignUpInteractor? = null
    private val job = Job() // hilo en segundo plano

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.signUpInteractor = signUpInteractor
    }


    override fun attachView(view: SignUpContract.SignUpView) {
        this.view = view
    }

    override fun isViewAttached(): Boolean {
        return view !=  null
    }

    override fun detachView() {
        view = null
    }

    override fun checkEmptyDni(dni: String): Boolean {
        return dni.isEmpty()
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswords(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkPasswordsMatch(pw1: String, pw2: String): Boolean {
        return pw1 == pw2
    }

    override fun signUp(dni: String, email: String, password: String) {
        view?.showProgress()
        signUpInteractor?.signUp(dni,email,password,object: SignUpInteractor.SignUpCallBack{
            override fun onRegisterSuccess() {
                view?.navigateToMain()
                view?.hideProgress()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgress()
            }
        })
    }


}