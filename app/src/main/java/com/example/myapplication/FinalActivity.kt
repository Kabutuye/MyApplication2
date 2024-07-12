


package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class FinalActivity : AppCompatActivity() {

    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var distanceInput: EditText
    private lateinit var calories_lost: TextView
    private lateinit var bmi_value: TextView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_acitivity)

        // Initialize views
        weightInput = findViewById(R.id.weight_input)
        heightInput = findViewById(R.id.height_input)
        distanceInput = findViewById(R.id.distance_input)
        calories_lost = findViewById(R.id.calories_lost)
        bmi_value = findViewById(R.id.bmi_value)
        val calculateButton: Button = findViewById(R.id.calculate_button)

        db = FirebaseFirestore.getInstance()

        // Set click listener for calculate button
        calculateButton.setOnClickListener {
            calculateAndSaveData()
        }
    }

    private fun calculateAndSaveData() {
        // Retrieve user input
        val weightStr = weightInput.text.toString()
        val heightStr = heightInput.text.toString()
        val distanceStr = distanceInput.text.toString()

        if (weightStr.isNotEmpty() && heightStr.isNotEmpty() && distanceStr.isNotEmpty()) {
            val weight = weightStr.toDouble()
            val height = heightStr.toDouble()
            val distance = distanceStr.toDouble()

            // Calculate calories lost
            val caloriesLost = calculateCaloriesLost(weight, distance)

            // Calculate BMI
            val bmi = calculateBMI(weight, height)

            // Update TextViews with calculated values
            calories_lost.text = "Calories Lost: ${caloriesLost.toInt()}"
            bmi_value.text = "BMI: ${bmi.format(1)}"

            // Save data to Firebase
            saveDataToFirebase(weight, height, distance, caloriesLost, bmi)
        }
    }

    private fun calculateCaloriesLost(weight: Double, distance: Double): Double {
        // Simple formula: calories burned per km = 1.036 * weight in kg
        return 1.036 * weight * distance
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        // BMI formula: weight / (height * height)
        return weight / (height * height)
    }

    private fun saveDataToFirebase(
        weight: Double,
        height: Double,
        distance: Double,
        caloriesLost: Double,
        bmi: Double
    ) {
        val userActivity = hashMapOf(
            "weight" to weight,
            "height" to height,
            "distance" to distance,
            "caloriesLost" to caloriesLost,
            "bmi" to bmi
        )

        db.collection("userActivities")
            .add(userActivity)
            .addOnSuccessListener { documentReference ->
                // Handle success (optional)
            }
            .addOnFailureListener { e ->
                // Handle failure (optional)
            }
    }

    // Extension function to format Double to specified decimal places
    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}








