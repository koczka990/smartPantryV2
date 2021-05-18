package hu.bme.aut.android.smartpantryv2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao{
    @Insert
    fun insertGrocery(grocery: RoomGrocery)

    @Query("SELECT * FROM grocerytable")
    fun getAllGroceries(): LiveData<List<RoomGrocery>>

    @Update
    fun updateGrocery(grocery: RoomGrocery): Int

    @Delete
    fun deleteGrocery(grocery: RoomGrocery)
}