package hu.bme.aut.android.smartpantryv2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.GroceryApplication
import hu.bme.aut.android.smartpantryv2.repository.Repository
import kotlinx.coroutines.launch

class GroceryViewModel : ViewModel() {

    private val repository: Repository

    val allGroceries: LiveData<List<Grocery>>

    init{
        val groceryDao = GroceryApplication.groceryDatabase.groceryDao()
        repository = Repository(groceryDao)
        allGroceries = repository.getAllGroceries()
    }

    fun insert(grocery: Grocery) = viewModelScope.launch {
        repository.insert(grocery)
    }

    fun update(grocery: Grocery) = viewModelScope.launch {
        repository.update(grocery)
    }

    fun updater(grocery: Grocery) = viewModelScope.launch {
        repository.update(grocery)
    }

}