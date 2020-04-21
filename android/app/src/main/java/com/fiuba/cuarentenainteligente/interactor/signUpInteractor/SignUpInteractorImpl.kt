package com.fiuba.cuarentenainteligente.interactor.signUpInteractor

import com.google.firebase.auth.FirebaseAuth

class SignUpInteractorImpl : SignUpInteractor{

    override fun signUp(dni: String, email: String, pw: String, listener: SignUpInteractor.SignUpCallBack) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pw).addOnCompleteListener {
            if (it.isSuccessful){
                listener.onRegisterSuccess()
            } else {
                listener.onRegisterFailure(it.exception?.message.toString())
            }
        }
    }
}