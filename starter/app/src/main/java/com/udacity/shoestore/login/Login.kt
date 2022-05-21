package com.udacity.shoestore.login

import android.app.Activity
import android.app.ActivityManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.udacity.shoestore.shoelist.ShoeListViewModel
import com.udacity.shoestore.welcomescreen.WelcomeScreenDirections

class Login : Fragment() {

    private  val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false )

        viewModel.credentialCorrect.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    viewModel.onCredentialCheckComplete()
                    navigateToWelcomeScreen()
                }
        })

        binding.button2.setOnClickListener {
                v: View -> v.findNavController().navigate(R.id.action_login2_to_welcomeScreen)
        }

        return binding.root
    }

    private fun navigateToWelcomeScreen() {
        val destination = WelcomeScreenDirections.actionWelcomeScreenToInstructions()
        findNavController(this).navigate(destination)

    }

}