package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {

    lateinit var Buttonreg:Button
    lateinit var emailinp:EditText
    lateinit var passinp:EditText
    lateinit var confpassinp:EditText
    lateinit var login: TextView

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //find our ids
        Buttonreg=findViewById(R.id.login_btn)
        emailinp = findViewById(R.id.edtEmail)
        passinp = findViewById(R.id.edtPassword)
        confpassinp =findViewById(R.id.edtConfPassword)
        login = findViewById(R.id.account)

        //initialize firebase Auth
        auth = Firebase.auth


        val login: TextView = findViewById(R.id.account)
        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


        Buttonreg.setOnClickListener {

            //user sign up method
            signUpUser()

        }


    }

    private fun signUpUser() {

        val userEmail = emailinp.text.toString()
        val userPass = passinp.text.toString()
        val confirmPass = confpassinp.text.toString()

        //perform input validation if blank
        if (userEmail.isBlank() || userPass.isBlank() || confirmPass.isBlank()) {
            Toast.makeText(this, "One of your inputs is empty", Toast.LENGTH_SHORT).show()
//chai , Juice , uji
        }
        //check if password is matching
        if (userPass != confirmPass) {
            Toast.makeText(this, "Sorry Password is not matching", Toast.LENGTH_SHORT).show()
        }
        //create user with firebase auth
        auth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(this) {
            if (it.isSuccessful ) {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_LONG).show()
                finish()
                //Navigate to another page
                var intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()

            }
            else {
                Toast.makeText(this, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "Error--->", it.exception)
            }
        }





    }
}