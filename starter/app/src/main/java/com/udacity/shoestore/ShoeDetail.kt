package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
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

/*
    This is for the save button

 */
        binding.shoeDetailSaveButton.setOnClickListener {

                v: View -> v.findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList(binding.shoeViewModel?.shoeItem!!))
        }


//        binding.shoeDetailSaveButton.setOnClickListener {
//                save
//        }


/*
    This is for the cancel button

 */
        binding.shoeDetailCancelButton.setOnClickListener {
                v: View -> v.findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList2(binding.shoeViewModel?.shoeItem!!))
        }
        return binding.root
    }

}