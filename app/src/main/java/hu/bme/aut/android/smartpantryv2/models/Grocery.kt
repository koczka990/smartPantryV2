package hu.bme.aut.android.smartpantry.models

class Grocery (val id: Int, val name: String, var quantity: Int, var toBuy: Int, val measreIn: String) {

    companion object {
        val baseIngredients = arrayOf("Potato", "Chicken", "Paprika", "Basil", "Tomato", "Ketchup", "Beef", "Pork", "Onion", "Wine", "Sugar", "Salt")
        fun createBaseList(numGroceries: Int): ArrayList<Grocery> {
            val groceries = ArrayList<Grocery>()
            for (i in 0 until (baseIngredients.size)) {
                groceries.add(Grocery(i, baseIngredients[i], 2, 0, "g"))
            }
            return groceries
        }
    }
}