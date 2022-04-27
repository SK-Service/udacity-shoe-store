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

//    init {
//        Log.i("ShoeListViewModel", "Init called")
//        _shoeList.value = addIntialListOfShoes()
//    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeListViewModel" , "onCleared called")
    }

    fun save (name:String, size:Double, company:String, description:String) {
        Log.i("ShoeListViewModel" , "Inside Save: Shoe Name: ${name}")
        addShoeItemToShoeList(name, size, company, description)
    }

    private fun addShoeItemToShoeList(name: String, numberSize: Double, company: String, description: String) {
        Log.i("ShoeListViewModel", "Inside addShoeItemToShoeList: Shoe Name: ${name}")
        val shoe:Shoe = Shoe(name, numberSize, company, description)
        _shoeList.value?.plus(Shoe(name, numberSize, company, description, emptyList()))
        Log.i("ShoeListViewModel", "before extracting shoe from LiveData")
        val trialShoe = _shoeList.value?.get(0)
        Log.i("ShoeListViewModel", "Before leaving addShoeItemToShoeList: Shoe Name: ${trialShoe?.name}")

    }

}