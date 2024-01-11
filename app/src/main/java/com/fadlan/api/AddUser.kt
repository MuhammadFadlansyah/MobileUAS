package com.fadlan.api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                val apiService = NetworkService.apiAdd
                val user = User(0,username, password, name, email)

                apiService.addUser(user).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AddUser, "User added successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@AddUser, "Failed to add user", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@AddUser, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@AddUser, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this@AddUser,ListUser::class.java)
            startActivity(intent)
        }
    }
}