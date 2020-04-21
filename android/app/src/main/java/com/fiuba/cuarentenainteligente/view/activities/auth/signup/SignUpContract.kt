package com.fiuba.cuarentenainteligente.view.activities.auth.signup

interface SignUpContract  {
    interface SignUpView{
        fun navigateToMain()
        fun signUp()
        fun showProgress()
        fun hideProgress()
        fun showError(errormsg:String)
    }

    interface SignUpPresenter{
        fun attachView(view:SignUpView)
        fun isViewAttached():Boolean
        fun detachView()
        fun checkEmptyDni(dni:String):Boolean
        fun checkValidEmail(email:String):Boolean
        fun checkEmptyPasswords(pw1:String,pw2:String):Boolean
        fun checkPasswordsMatch(pw1:String,pw2:String):Boolean
        fun signUp(dni:String,email:String,password:String)
    }
}