package com.fadlan.api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        val listview = findViewById<ListView>(R.id.listview)

        val buttonAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        buttonAdd.setOnClickListener{
            val intentadd = Intent(this@ListUser, AddUser::class.java)
            startActivity(intentadd)
        }

        NetworkService.apiData.getUserData().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val userList: List<User>? = response.body()
                    val adapter = userList?.let { AdapterUser(this@ListUser, it) }
                    listview.adapter = adapter

                    // Tambahkan event click ke ListView
                    listview.setOnItemClickListener { parent, view, position, id ->
                        val selectedUser = adapter?.getItem(position) as User // Menggunakan model User
                        val intent = Intent(this@ListUser, UpdateDelete::class.java)
                        val user2 = User(selectedUser.userid, selectedUser.username, selectedUser.password, selectedUser.name, selectedUser.email)

                        // Mengirim data sebagai User2 ke halaman UpdateDelete
                        intent.putExtra("user", user2)
                        startActivity(intent)

//                        if (!isFinishing && !isDestroyed) {
//                            startActivity(intent)
//                        }
                        startActivity(intent)
                    }
                } else {
                    showToast("Gagal mendapatkan data pengguna")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showToast("Terjadi kesalahan: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this@ListUser, message, Toast.LENGTH_SHORT).show()
    }
}
