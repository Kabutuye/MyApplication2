package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import net.ezra.R
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var getstarted:Button
    private lateinit var getskipping:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        getstarted = findViewById(R.id.getStartedButton)
        getskipping = findViewById(R.id.skip)

        getstarted.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val getskipping: TextView = findViewById(R.id.skip)
        getskipping.setOnClickListener {
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
        }




    }



}
