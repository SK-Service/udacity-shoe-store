package com.udacity.shoestore.login

import android.app.Activity
import android.app.ActivityManager
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.models.User
import com.udacity.shoestore.models.UserProfile
import com.udacity.shoestore.shoelist.ShoeListViewModel
import com.udacity.shoestore.welcomescreen.WelcomeScreenDirections

class Login : Fragment() {

    private  val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Login", "Inside onCreateView")
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false )

        //Set the binding properties
        binding.loginViewModel = viewModel
        binding.user = User("","")
        binding.userProfile = UserProfile("", "")

        //Observe to sign in the user when the credentialcorrect property is set
        viewModel.credentialCorrect.observe(viewLifecycleOwner, Observer {
            Log.i("Login", "value of credentialCorrect is : <${it}>")
            Log.i("Login", "value of userLoggedOut is : <${viewModel.userLoggedOut.value!!}>")
                if (it == true) {
                    if (viewModel.userLoggedOut.value!! == true) {
                        binding.message.text = "You are successfully logged out"
                        binding.message.setTextColor(Color.parseColor("#007500"))
                        //userLoggedOut value to false - the flag is a misnomer
                        // this can create confusion
                        viewModel.userIsLoggedIn()
                    } else {
                        Log.i("Login", "Inside the Credential Verification oberserver")
//                        viewModel.onCredentialCheckComplete()
                        viewModel.userIsLoggedIn()
                        navigateToWelcomeScreen()
                        Log.i("Login", "Existing the Observer")
                    }
                }else if (it == false){
                    binding.message.text = "User name or password entered is incorrect"
                }
        })
        return binding.root
    }

    private fun navigateToWelcomeScreen() {
        Log.i("Login", "inside navigateToWelcomeScreen")
        val destination = LoginDirections.actionLogin2ToWelcomeScreen()
        findNavController(this).navigate(destination)
        Log.i("Login", "after find NavController.navigate")
    }

}