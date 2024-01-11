package com.fadlan.api

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var signupName: EditText
    private lateinit var signupUsername: EditText
    private lateinit var signupEmail: EditText
    private lateinit var signupPassword: EditText
    private lateinit var loginRedirectText: TextView
    private lateinit var signupButton: Button
    private var database: FirebaseDatabase? = null
    private var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        signupName = findViewById(R.id.signup_name)
        signupEmail = findViewById(R.id.signup_email)
        signupUsername = findViewById(R.id.signup_username)
        signupPassword = findViewById(R.id.signup_password)
        loginRedirectText = findViewById(R.id.loginRedirectText)
        signupButton = findViewById(R.id.signup_button)
    }

    private fun setupListeners() {
        signupButton.setOnClickListener {
            handleSignUp()
        }

        loginRedirectText.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun handleSignUp() {
        val name = signupName.text.toString()
        val email = signupEmail.text.toString()
        val username = signupUsername.text.toString()
        val password = signupPassword.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
            database = FirebaseDatabase.getInstance()
            // Using the root reference of the database
            reference = database?.reference?.child("users")

            val helperClass = HelperClass(name, email, username, password)
            reference?.child(username)?.setValue(helperClass)
            Toast.makeText(
                this@SignUp,
                "You have signed up successfully!",
                Toast.LENGTH_SHORT
            ).show()

            navigateToLogin()
        } else {
            Toast.makeText(this@SignUp, "Please fill in all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@SignUp, Login::class.java)
        startActivity(intent)
    }
}