package com.fadlan.api

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDelete : AppCompatActivity() {

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        val receivedUser = intent.getParcelableExtra<User>("user")

        if (receivedUser != null) {
            val userIdTextView = findViewById<TextView>(R.id.idData)
            val usernameEditText = findViewById<EditText>(R.id.usernameUpdate)
            val passwordEditText = findViewById<EditText>(R.id.passwordUpdate)
            val nameEditText = findViewById<EditText>(R.id.nameUpdate)
            val emailEditText = findViewById<EditText>(R.id.emailUpdate)

            userIdTextView.text = receivedUser.userid.toString()
            usernameEditText.setText(receivedUser.username)
            passwordEditText.setText(receivedUser.password)
            nameEditText.setText(receivedUser.name)
            emailEditText.setText(receivedUser.email)

            val updateButton = findViewById<Button>(R.id.updateButton)
            val deleteButton = findViewById<Button>(R.id.deleteButton)

            updateButton.setOnClickListener {
                val updatedUser = User(
                    receivedUser.userid,
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    nameEditText.text.toString(),
                    emailEditText.text.toString()
                )

                // Mengonversi objek User menjadi JSON String
                val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), gson.toJson(updatedUser))

                // Menjalankan permintaan dengan Retrofit
                NetworkService.apiUpdate.updateUser(requestBody).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        // Handle tanggapan dari server
                        if (response.isSuccessful) {
                            showToast("User updated")
                        } else {
                            showToast("Failed to update user. ${response.code()}: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // Handle kegagalan permintaan
                        showToast("Error: ${t.message}")
                    }
                })
                val intent = Intent(this@UpdateDelete,ListUser::class.java)
                startActivity(intent)
            }

            deleteButton.setOnClickListener {
                NetworkService.apiDelete.deleteUser(receivedUser.userid).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            showToast("User deleted")
                        } else {
                            showToast("Failed to delete user")
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        showToast("Error: ${t.message}")
                    }
                })
                val intent2 = Intent(this@UpdateDelete,ListUser::class.java)
                startActivity(intent2)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@UpdateDelete, message, Toast.LENGTH_SHORT).show()
    }
}
