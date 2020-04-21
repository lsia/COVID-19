package com.fiuba.cuarentenainteligente.interactor.loginInteractor

interface LoginInteractor {

    //el suspend permite que se ejecute en un hilo aparte
    suspend fun signInWithEmailAndPassword(email:String,password:String)

}