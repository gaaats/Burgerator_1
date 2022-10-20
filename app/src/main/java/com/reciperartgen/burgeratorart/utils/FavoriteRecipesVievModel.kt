package com.reciperartgen.burgeratorart.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reciperartgen.burgeratorart.entitys.ResponseApiSingleRecipe

class FavoriteRecipesVievmodel: ViewModel() {

    private var _listFavoriteRecipes = MutableLiveData<MutableList<ResponseApiSingleRecipe>>()
    val listFavoriteRecipes: LiveData<MutableList<ResponseApiSingleRecipe>>
        get() = _listFavoriteRecipes

    private var _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty: LiveData<Boolean>
        get() = _isListEmpty


    init {

        _listFavoriteRecipes.value = mutableListOf()
    }




    fun addToShopCart(singleRecipe: ResponseApiSingleRecipe) {
        Log.d("favorit", "in vievmodel pressed add")
        if (checkIsItemAlreadyInShopCart(singleRecipe)) {
            return
        }
        val savedList = mutableListOf<ResponseApiSingleRecipe>()
        _listFavoriteRecipes.value?.forEach {
            savedList.add(it)
        }
        savedList.add(singleRecipe)
        _listFavoriteRecipes.value = savedList
        makeShopCartStateIsFull()
    }



    private fun makeShopCartStateIsFull() {
        _isListEmpty.value = false
    }

    private fun checkIsItemAlreadyInShopCart(singleRecipe: ResponseApiSingleRecipe): Boolean {
        _listFavoriteRecipes.value!!.forEach {
            if (it._title == singleRecipe._title) return true
        }
        return false
    }


}