package com.fadlan.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "http://192.168.2.144/project/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiData: ApiData = retrofit.create(ApiData::class.java)
    val apiAdd: ApiAdd = retrofit.create(ApiAdd::class.java)
    val apiUpdate : ApiUpdate = retrofit.create(ApiUpdate::class.java)
    val apiDelete : ApiDelete = retrofit.create(ApiDelete::class.java)
}