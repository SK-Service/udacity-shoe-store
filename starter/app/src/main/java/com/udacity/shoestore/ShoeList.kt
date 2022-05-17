package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeItemBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LoginFragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeList.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeList : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false )
        Log.i("Fragment-ShoeList", "inside onCreateView before setting Menu")
        setHasOptionsMenu(true)

        Log.i("Fragment-ShoeList", "inside onCreateView before setting Observer")
        viewModel.saveShoeList.observe(viewLifecycleOwner, Observer {
            Log.i("Fragment-ShoeList", "Observer called")
            for (shoe in viewModel.shoeList.value!!) {
                val inBinding = FragmentShoeItemBinding.inflate(layoutInflater)
                inBinding.shoeData = shoe
                binding.scrollView2.addView(inBinding.root)
            }
        })

        binding.addShoeButton.setOnClickListener {
                v: View -> v.findNavController().navigate(R.id.action_shoeList_to_shoeDetail)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.logout_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
                    requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}