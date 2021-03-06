package com.udacity.shoestore.welcomescreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeScreenFragmentBinding

class WelcomeScreen : Fragment() {

    companion object {
        fun newInstance() = WelcomeScreen()
    }

    private lateinit var viewModel: WelcomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: WelcomeScreenFragmentBinding =DataBindingUtil.inflate(
                inflater, R.layout.welcome_screen_fragment, container,false )

        binding.goToInstructionButton.setOnClickListener {
            v : View -> v.findNavController().navigate(R.id.action_welcomeScreen_to_instructions)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}