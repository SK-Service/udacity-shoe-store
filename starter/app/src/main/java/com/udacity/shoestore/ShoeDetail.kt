package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController


import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoelist.ShoeListViewModel
import java.security.KeyStore


class ShoeDetail : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ShoeDetail-Fragment", "Inisde onCreateView - Just entered")
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false )

        binding.shoelistViewModel = viewModel
        binding.shoeItem = Shoe("", 0.0, "", "", emptyList())


        viewModel.saveShoeList.observe(this as LifecycleOwner, Observer {
            if (it == true) {
                    Log.i("ShoeDetail-Fragment", "Before setting onShoeSave to false")
                    viewModel.onShoeSaveComplete()
                    Log.i("ShoeDetail-Fragment", "Before calling navigate to shoe list")
                    navigateToShoeList()
                }
            Log.i("ShoeDetail-Fragment", "exiting out of the observer")
        })

/*
    This is for the cancel button

 */
        binding.shoeDetailCancelButton.setOnClickListener {
                v: View -> v.findNavController().navigate(R.id.action_shoeDetail_to_shoeList)
        }


        return binding.root
    }

    private fun navigateToShoeList() {
        Log.i("ShodeDetail", "inside navigateToShoeList")
        val destination = ShoeDetailDirections.actionShoeDetailToShoeList()
        Log.i("ShodeDetail", "before NavController.Navigate:<${destination}>")
        Log.i("ShodeDetail", "before NavController.Navigate, this:<${this.toString()}>")
//
//        this.findNavController().navigate(destination)
        findNavController(this).navigate(destination)
    }

}