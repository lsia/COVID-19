package com.fiuba.cuarentenainteligente.view.activities.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.base.BaseActivity
import com.fiuba.cuarentenainteligente.interactor.signUpInteractor.SignUpInteractor
import com.fiuba.cuarentenainteligente.interactor.signUpInteractor.SignUpInteractorImpl
import com.fiuba.cuarentenainteligente.view.activities.SelectorActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity(), SignUpContract.SignUpView {

    lateinit var presenter: SignUpPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SignUpPresenter(SignUpInteractorImpl())
        presenter.attachView(this)

        btn_signUp.setOnClickListener {
            signUp()
        }

    }

    override fun getLayout(): Int {
        return (R.layout.activity_sign_up)
    }

    override fun navigateToMain() {
        val intent = Intent(this,SelectorActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val dni:String = etx_dni.text.toString().trim()
        val email:String = etxt_email_register.text.toString().trim()
        val pw1:String = etx_pw1.text.toString().trim()
        val pw2:String = etx_pw2.text.toString().trim()

        if(presenter.checkEmptyDni(dni)){
            etx_dni.error = "The name is empty."
            return
        }

        if(!presenter.checkValidEmail(email)){
            etxt_email_register.error = "The email is invalid"
            return
        }

        if(presenter.checkEmptyPasswords(pw1,pw2)){
            etx_pw1.error = "Empty field"
            etx_pw2.error = "Empty field"
            return
        }

        if(!presenter.checkPasswordsMatch(pw1,pw2)){
            etx_pw1.error = "Passwords dont match"
            etx_pw2.error = "Passwords dont match"
            return
        }

        presenter.signUp(dni,email,pw1)
    }

    override fun showProgress() {
        progress_signup.visibility = View.VISIBLE
        btn_signUp.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_signup.visibility = View.GONE
        btn_signUp.visibility = View.VISIBLE
    }

    override fun showError(errormsg: String) {
        toast(this,errormsg)
    }
}
