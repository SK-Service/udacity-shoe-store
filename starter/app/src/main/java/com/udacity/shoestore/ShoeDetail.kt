package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import com.udacity.shoestore.models.Shoe


class ShoeDetail : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false )

        binding.shoelistViewModel = viewModel
      binding.shoeItem = Shoe("", 0.0, "", "", emptyList())
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            Log.i("ShodeDetail", "inside the ShoeDetail Observer and before calling navigateToShoeList")
                navigateToShoeList()
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
        Log.i("ShodeDetail", "inside tnavigateToShoeList")
           findNavController().navigate(R.id.action_shoeDetail_to_shoeList)
    }

}