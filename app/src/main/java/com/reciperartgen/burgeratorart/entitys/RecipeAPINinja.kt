package com.reciperartgen.burgeratorart.entitys

import com.reciperartgen.burgeratorart.BuildConfig
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPINinja {

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
        const val NAME_FOOD = "burger"
    }


    @GET("recipe")
    suspend fun getRecipes(
        @Query("query") query : String = NAME_FOOD,
    ): Response<ResponseApiList>


}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", RecipeAPINinja.API_KEY)
            .build()
        return chain.proceed(request)
    }
}