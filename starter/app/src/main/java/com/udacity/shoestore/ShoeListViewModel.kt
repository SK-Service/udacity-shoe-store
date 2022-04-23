package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel (){

    var shoeItem : Shoe? = null

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        Log.i("ShoeListViewModel", "Init called")
        _shoeList.value = addIntialListOfShoes()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeListViewModel" , "onCleared called")
    }

    private fun addIntialListOfShoes(): List<Shoe> {
        val shoeList = mutableListOf<Shoe>()

        shoeList.add(Shoe("Crossfit", 8.5, "Adidas", "Top of the line cross fit shoes"))
        shoeList.add(Shoe("Office wear", 8.5, "Sketcher", "For long day style and health"))

        return shoeList
    }
}