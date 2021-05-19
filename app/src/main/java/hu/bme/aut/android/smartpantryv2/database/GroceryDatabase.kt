package hu.bme.aut.android.smartpantryv2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 2,
    exportSchema = false,
    entities = [RoomGrocery::class]
)
abstract class GroceryDatabase : RoomDatabase(){

    abstract fun groceryDao(): GroceryDao

}