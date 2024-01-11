package com.fadlan.api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUpdate {
    @POST("update.php")
    fun updateUser(@Body request: RequestBody): Call<Void>
}
