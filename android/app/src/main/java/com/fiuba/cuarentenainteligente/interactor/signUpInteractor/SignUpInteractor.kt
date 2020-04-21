package com.fiuba.cuarentenainteligente.interactor.signUpInteractor

interface SignUpInteractor {

    interface SignUpCallBack{
        fun onRegisterSuccess()
        fun onRegisterFailure(errorMsg:String)
    }

    fun signUp (dni:String, email:String, pw:String,listener:SignUpCallBack)
}