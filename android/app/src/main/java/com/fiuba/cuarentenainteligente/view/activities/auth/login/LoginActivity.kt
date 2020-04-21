package com.fiuba.cuarentenainteligente.view.activities.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.base.BaseActivity
import com.fiuba.cuarentenainteligente.interactor.loginInteractor.LoginInteractor
import com.fiuba.cuarentenainteligente.interactor.loginInteractor.LoginInteractorImpl
import com.fiuba.cuarentenainteligente.view.activities.SelectorActivity
import com.fiuba.cuarentenainteligente.view.activities.auth.passRecover.PassRecoveryActivity
import com.fiuba.cuarentenainteligente.view.activities.auth.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(LoginInteractorImpl())
        presenter.attachView(this)

        btn_signIn.setOnClickListener {
            signIn()
        }

        txt_register.setOnClickListener {
            navigateToRegister()
        }

        txt_password_recover.setOnClickListener {
            navigateToPasswordRecover()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgressBar() {
        progressBar_signIn.visibility = View.VISIBLE
        btn_signIn.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBar_signIn.visibility = View.GONE
        btn_signIn.visibility = View.VISIBLE
    }

    override fun signIn() {
        val email = etxt_email.text.toString().trim()
        val password = etxt_password.text.toString().trim()

      if(presenter.checkEmptyFields(email, password))  toast (this, "Usuario o contrasaeña vacios")
        else presenter.signInUserWithEmailAndPassword(email, password)
    }

    override fun navigateToMain() {
        val intent = Intent(this,SelectorActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

    override fun navigateToRegister() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    override fun navigateToPasswordRecover() {
        startActivity(Intent(this, PassRecoveryActivity::class.java))

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }
}
