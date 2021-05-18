package hu.bme.aut.android.smartpantryv2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.database.GroceryDao
import hu.bme.aut.android.smartpantryv2.database.RoomGrocery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val groceryDao: GroceryDao){

    fun getAllGroceries(): LiveData<List<Grocery>> {
        return groceryDao.getAllGroceries()
            .map {roomGroceries ->
                roomGroceries.map {roomGrocery ->
                    roomGrocery.toDomainModel()
                }
            }
    }

    suspend fun insert(grocery: Grocery) = withContext(Dispatchers.IO){
        groceryDao.insertGrocery(grocery.toRoomModel())
    }

    private fun RoomGrocery.toDomainModel(): Grocery {
        return Grocery(
            id = id,
            name = name,
            quantity = quantity,
            measreIn = measreIn
        )
    }

    private fun Grocery.toRoomModel(): RoomGrocery {
        return RoomGrocery(
            name = name,
            quantity = quantity,
            measreIn = measreIn
        )
    }

}