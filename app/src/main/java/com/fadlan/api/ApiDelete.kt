package com.fadlan.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiDelete {
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteUser(@Field("userid") userId: Int): Call<Void>
}
