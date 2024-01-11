package com.fadlan.api

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private lateinit var namaUserTextView: TextView
    private lateinit var frunameTextView: TextView
    private lateinit var fremailTextView: TextView
    private lateinit var frpassTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        namaUserTextView = view.findViewById(R.id.namauser)
        frunameTextView = view.findViewById(R.id.fruname)
        fremailTextView = view.findViewById(R.id.fremail)
        frpassTextView = view.findViewById(R.id.frpass)
        sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userRef = database.reference.child("users")

        loadDataFromFirebase()

        val btnKeluar: Button = view.findViewById(R.id.btnkeluar)

        btnKeluar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            redirectToLogin()
        }

        return view
    }

    private fun loadDataFromFirebase() {
        val savedUsername = sharedPreferences.getString("username", "")

        userRef.orderByChild("username").equalTo(savedUsername).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val userData = userSnapshot.getValue(HelperClass::class.java)

                    namaUserTextView.text = userData?.name
                    frunameTextView.text = userData?.username
                    fremailTextView.text = userData?.email
                    frpassTextView.text = userData?.password
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handling cancellation
            }
        })
    }

    private fun redirectToLogin() {
        val intent = Intent(requireContext(), Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}