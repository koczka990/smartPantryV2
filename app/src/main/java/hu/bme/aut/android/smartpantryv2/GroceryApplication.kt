package hu.bme.aut.android.smartpantryv2

import android.app.Application
import androidx.room.Room
import hu.bme.aut.android.smartpantryv2.database.GroceryDatabase

class GroceryApplication : Application() {

    companion object {
        lateinit var groceryDatabase: GroceryDatabase
                private set
    }

    override fun onCreate() {
        super.onCreate()

        groceryDatabase = Room.databaseBuilder(
            applicationContext,
            GroceryDatabase::class.java,
            "grocery_database"
        ).fallbackToDestructiveMigration().build()
    }

}