package com.udacity.shoestore.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _user: MutableLiveData<User> = MutableLiveData()
    val loginUserName: LiveData<User>
        get() = _user

    private var _credentialCorrect = MutableLiveData<Boolean>()
    val credentialCorrect: LiveData<Boolean>
        get() = _credentialCorrect

    fun onCredentialCheckComplete() {
        _credentialCorrect.value = false
    }

    fun checkLoginUserName(loginID: String) : Boolean {
        return loginID == UserName.USER_NAME

    }

    fun checkPassword(passwd: String) : Boolean {
        return passwd == UserName.USER_PASSWORD

    }

    companion object UserName {
        val USER_NAME: String = "shoe@store"
        val USER_PASSWORD: String= "shoepass"
    }

    fun check (userName: String, userPassword: String ) {
        Log.i("LoginViewModel", "Inside the check function to validate credentials")
        Log.i("LoginViewModel", "UserName:<${userName}, Password:<${userPassword}>")
        if ( checkLoginUserName(userName) &&
                checkPassword(userPassword)  ) {
            Log.i("LoginViewModel", "Crecentials valid")
            _credentialCorrect.value = true
        }
    }


}