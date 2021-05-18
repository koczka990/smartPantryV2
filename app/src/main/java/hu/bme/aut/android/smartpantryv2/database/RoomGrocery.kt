package hu.bme.aut.android.smartpantryv2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocerytable")
data class RoomGrocery(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val measreIn: String
)