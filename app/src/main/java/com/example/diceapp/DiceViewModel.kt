package com.example.diceapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceViewModel : ViewModel() {

    private var _dice = MutableLiveData<Int>()

    fun getRolls() : MutableLiveData<Int>{
        Log.i(TAG,"get Number")
        if (_dice.value == null){
            _dice = MutableLiveData()
            getRandomRolls()
        }
        return _dice
    }
    fun getRandomRolls() {
        Log.i(TAG,"create random Number")
        _dice.value = Random.nextInt(1, 6)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG,"ViewModel Destroyed")
    }

    companion object{
        private const val TAG : String = "View Model"
    }
}