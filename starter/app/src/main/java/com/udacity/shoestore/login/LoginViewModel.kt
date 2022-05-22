package com.udacity.shoestore.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User
import com.udacity.shoestore.models.UserProfile

class LoginViewModel : ViewModel() {
    //to hold the user entered credential
    private val _user: MutableLiveData<User> = MutableLiveData()
    val loginUserName: LiveData<User>
        get() = _user

    //to hold the user entered credential for sign up
    private val _userProfile = MutableLiveData<MutableList<UserProfile>>(mutableListOf())
    val userprofile: LiveData<MutableList<UserProfile>>
        get() = _userProfile

    // to check if the entered credential are valid
    private var _credentialCorrect = MutableLiveData<Boolean>()
    val credentialCorrect: LiveData<Boolean>
        get() = _credentialCorrect

    //keep track of whether user is logged out
    private var _userLoggedOut = MutableLiveData<Boolean>(false)
    val userLoggedOut: LiveData<Boolean>
        get() = _userLoggedOut

    fun onCredentialCheckComplete() {
        _credentialCorrect.value = false
    }

    fun checkLoginUserName(loginID: String) : Boolean {
        return loginID == UserName.USER_NAME

    }

    fun checkPassword(passwd: String) : Boolean {
        return passwd == UserName.USER_PASSWORD

    }

    //There is a default user name and password to the app
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
        } else if (validateUserCredential( userName, userPassword)) {
            Log.i("LoginViewModel", "Inside the validateUserCredential")
            _credentialCorrect.value = true
        }
        else {
            _credentialCorrect.value = false
        }
    }

    //Validate against a user set user name and password during sign-up
    private fun validateUserCredential(userName: String, userPassword: String) : Boolean {
        Log.i("LoginViewModel", "inside validateUserCredential")
        var findAMatch: Boolean = false
        _userProfile.value!!.forEach match@{
            if (it.userName.toString() == userName && it.password.toString() == userPassword) {
                Log.i("LoginViewModel", "Inside validateUserCredential - found a match")
                findAMatch = true
                return@match
            }
        }
        return findAMatch
    }

    //Capturing the user name and password during signup
    fun signUp(userName: String, userPassword: String) {
            //Create a User profile with user name and password and add to the User Profile
            //MutableLiveData - so that it can be used during the launched playing around with the
            //app
            Log.i("LoginViewModel", "Inside signUp")
            _userProfile.value?.add(CreateUSerProfile(userName, userPassword))
            //to keep it simple at this point, we would call check to join this to the same
            //flow as the sign-in
            check(userName, userPassword)
    }

    //Create and store the user name and password in the User Profile
    private fun CreateUSerProfile(userName: String, userPassword: String) : UserProfile {
            return UserProfile(userName, userPassword)
    }
    // to indicate that the user has successfully logged out
    fun logUserOut() {
        _userLoggedOut.value = true
    }

    // to indicate that the user has successfully logged out
    fun userIsLoggedIn() {
        _userLoggedOut.value = false
    }



}