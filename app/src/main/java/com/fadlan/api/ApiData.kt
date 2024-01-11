package com.fadlan.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiData {
    @GET("getuser.php")
    fun getUserData(): Call<List<User>>
}
