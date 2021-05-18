package hu.bme.aut.android.smartpantry.models

class Grocery (val id: Int, val name: String, val quantity: Int, val measreIn: String) {

    companion object {
        val baseIngredients = arrayOf("Potato", "Chicken", "Paprika", "Basil", "Tomato", "Ketchup", "Beef", "Pork", "Onion")
        fun createBaseList(numGroceries: Int): ArrayList<Grocery> {
            val groceries = ArrayList<Grocery>()

            for (i in 0 until (baseIngredients.size - 1)) {
                groceries.add(Grocery(i, baseIngredients[i], 2, "g"))
            }
            return groceries
        }
    }
}