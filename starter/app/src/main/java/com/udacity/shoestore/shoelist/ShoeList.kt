package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeItemBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_shoe_list.*
import kotlinx.android.synthetic.main.fragment_shoe_list.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeList.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeList : Fragment() {

    private  val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding
    private val loginViewModel: LoginViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false )

        binding.lifecycleOwner = this
        Log.i("Fragment-ShoeList", "inside onCreateView before setting Menu")
        setHasOptionsMenu(true)

        Log.i("Fragment-ShoeList", "inside onCreateView before setting Observer")
        viewModel.saveShoeList.observe(viewLifecycleOwner, Observer {
            Log.i("Fragment-ShoeList", "Observer called")


            if (!viewModel.shoeList.value.isNullOrEmpty()) {
                Log.i("Fragment-ShoeList", "Shoe List is not empty")

                binding.constraintLayoutParent.removeView(textView3)

                viewModel.shoeList.value!!.forEach {

                    val shoeItemBinding: FragmentShoeItemBinding = DataBindingUtil.inflate(
                        inflater, R.layout.fragment_shoe_item, container, false )

                    shoeItemBinding.companyName.text = it.company
                    shoeItemBinding.shoeName.text = it.name
                    shoeItemBinding.shoeSize.text = it.size.toString()
                    shoeItemBinding.description.text = it.description
                    binding.scrollView2.shoe_list.addView(shoeItemBinding.shoeItemCardView)
                }
            }
        } )


        binding.addShoeButton.setOnClickListener {
                v: View ->
            run {
                Log.i("Fragment-ShoeList", "Inside OnClick Listener - about to navigate")
                v.findNavController().navigate(R.id.action_shoeList_to_shoeDetail)
            }
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.logout_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("ShoeList", "inside onOptionsItemSelected")
        Log.i("ShoeList", "inside Before calling log user out")
        loginViewModel.logUserOut()
        Log.i("ShoeList", "inside after calling log user out, userloggedout:<${loginViewModel.userLoggedOut}>")
        return NavigationUI.onNavDestinationSelected(item,
                    requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}