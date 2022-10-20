package com.reciperartgen.burgeratorart.utils

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FakeeeApiModule {

//    /// it is faaaaake
//    @Provides
//    @Singleton
//    fun providesNevsAPIService(): RecipeService {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.API_KEY)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create(RecipeService::class.java)
//    }
//
//    fun fakeFun(){
//        providesNevsAPIService()
//    }
}