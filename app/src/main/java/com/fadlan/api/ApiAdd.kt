package com.fadlan.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAdd {
    @POST("postuser.php")
    fun addUser(@Body user: User): Call<User>
}