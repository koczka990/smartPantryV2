package hu.bme.aut.android.smartpantry.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.android.smartpantry.models.Recipe
import hu.bme.aut.android.smartpantry.models.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.concurrent.thread

class RecipeInteractor {
    private val recipeAPI: RecipesAPI

    init {
        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(RecipesAPI.ENDPOINT_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        this.recipeAPI = retrofit.create(RecipesAPI::class.java)
    }

    private fun <T> runCallOnBackgroundThread(
            call: Call<T>,
            onSuccess: (T) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        val handler = Handler(Looper.getMainLooper()!!)
        thread {
            try {
                val response = call.execute().body()!!
                //Log.d("RESPONSE", response.toString())
                handler.post { onSuccess(response) }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }
    }

    fun getRecipes(
        onSuccess: (Response) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d("GETRECIPES", "getRecipes()")
        val getImagesRequest = recipeAPI.getRecipes()
        runCallOnBackgroundThread(getImagesRequest, onSuccess, onError)
    }
}