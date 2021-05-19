package hu.bme.aut.android.smartpantry.network

import hu.bme.aut.android.smartpantry.models.Recipe
import hu.bme.aut.android.smartpantry.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart

interface RecipesAPI {

    companion object {
        const val ENDPOINT_URL = "http://www.themealdb.com/api/json/v1/1/"
        const val UTF_8 = "UTF-8"
    }

    @GET("search.php?s=Chicken")
    fun getRecipes(): Call<Response>


    fun getMealsByIngredient(ingredient : String) : String {
        return ""
    }

}