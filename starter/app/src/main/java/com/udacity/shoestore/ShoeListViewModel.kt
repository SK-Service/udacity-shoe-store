package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel (){

    var shoeItem : Shoe? = null

    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: MutableLiveData<MutableList<Shoe>>
        get() = _shoeList

    private var _saveShoeInList = MutableLiveData<Boolean>()
    val saveShoeList: LiveData<Boolean>
        get() = _saveShoeInList

    init {
        Log.i("ShoeListViewModel", "initializing ShoeListviewModel")
        //Set SaveShoeInList to false so that it can be set to true to trigger shoe save
        _saveShoeInList.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeListViewModel" , "onCleared called")
    }

    fun save (name:String, size:Double, company:String, description:String) {

        Log.i("ShoeListViewModel" , "Inside Save: Shoe Name: ${name}")
        addShoeItemToShoeList(name, size, company, description)
        _saveShoeInList.value = true
    }

    private fun addShoeItemToShoeList(name: String, numberSize: Double, company: String, description: String) {
        Log.i("ShoeListViewModel", "Inside addShoeItemToShoeList: Shoe Name: ${name}")
        val shoe:Shoe = Shoe(name, numberSize, company, description, emptyList())
        _shoeList.value?.add(shoe)
        Log.i("ShoeListViewModel", "before extracting shoe from LiveData")
        val trialShoe = _shoeList.value?.get(0)
        Log.i("ShoeListViewModel", "Before leaving addShoeItemToShoeList: Shoe Name: ${trialShoe?.name}")

    }

    //After shoe is saved the _saveShoeInList boolean flag needs to be set to false
    fun onShoeSaveComplete() {
        _saveShoeInList.value = false
    }

}