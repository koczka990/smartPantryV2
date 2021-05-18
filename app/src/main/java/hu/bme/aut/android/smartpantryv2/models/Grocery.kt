package hu.bme.aut.android.smartpantry.models

class Grocery (val name: String, val quantity: Int, val measreIn: String) {

    companion object {
        fun createBaseList(numGroceries: Int): ArrayList<Grocery> {
            val groceries = ArrayList<Grocery>()
            for (i in 1..numGroceries) {
                groceries.add(Grocery("Paprika", 2, "Fej"))
            }
            return groceries
        }
    }
}