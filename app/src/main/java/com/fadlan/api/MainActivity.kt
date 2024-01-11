package com.fadlan.api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.fadlan.api.adapter.Adapter
import com.fadlan.api.data.ModelListGempa
import com.fadlan.api.engine.DataConfigJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val _listview = findViewById<ListView>(R.id.listview)

        DataConfigJSON()
            .getService()
            .getDataGempa()
            .enqueue(object : Callback<ModelListGempa>{
                override fun onResponse(
                    call: Call<ModelListGempa>,
                    response: Response<ModelListGempa>
                ) {
                    Log.d("fadlanzikri","data json: "+response.body())
                    _listview.adapter = Adapter(response.body(),this@MainActivity,
                        response.body()?.infogempa?.gempa!!
                    )
                }

                override fun onFailure(call: Call<ModelListGempa>, t: Throwable) {
                    Log.d("fadlanzikri","error: "+t.message.toString())
                }

            })
    }
}