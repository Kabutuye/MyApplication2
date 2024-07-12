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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {


    private lateinit var btnLogin:Button
    private lateinit var createAcc:Button
    private lateinit var emailedt:EditText
    private lateinit var passwordedt:EditText
    private lateinit var register:TextView


    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        emailedt = findViewById(R.id.edtEmail)
        passwordedt = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.login_btn)
        createAcc = findViewById(R.id.acc_btn)
        register = findViewById(R.id.register)

        //initialize firebase
        auth = Firebase.auth


        createAcc.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val register: TextView = findViewById(R.id.register)
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }






        btnLogin.setOnClickListener {

            loginUser()

        }





    }

    private fun loginUser() {
        //Toast.makeText(this, "Trying to Login", Toast.LENGTH_SHORT).show()
        //Convert inputs to string
        val userEmail = emailedt.text.toString()
        val userPassword = passwordedt.text.toString()

        //Toast.makeText(this, "email is $userEmail and password is $userPassword", Toast.LENGTH_LONG).show()

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                //means if task is successful navigate to landing page
                val intent = Intent(this, Activities2::class.java)
                startActivity(intent)


            }
            else {
                Toast.makeText(this, "Failed to Sign in", Toast.LENGTH_LONG).show()

                Log.d("TAG", "Error--->", it.exception)
            }
        }






    }
}
