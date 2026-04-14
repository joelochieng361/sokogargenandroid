package com.example.sokogarden

import ApiHelper
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // find all views by use of their ids
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val signupButton = findViewById<Button>(R.id.signupBtn)
        val signinTextView = findViewById<TextView>(R.id.signintxt)

        //Below when a person clicks on Textview, it navigates me to the signin page
        signinTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

        //Below when a person clicks on the button, it validates and sends data to api
        signupButton.setOnClickListener {
            // Get values from edittexts
            val nameVal = username.text.toString().trim()
            val emailVal = email.text.toString().trim()
            val passwordVal = password.text.toString().trim()
            val phoneVal = phone.text.toString().trim()

            // Validation logic
            if (nameVal.isEmpty()) {
                username.error = "Please enter username"
                username.requestFocus()
                return@setOnClickListener
            }
            if (emailVal.isEmpty()) {
                email.error = "Please enter email"
                email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailVal).matches()) {
                email.error = "Please enter a valid email"
                email.requestFocus()
                return@setOnClickListener
            }
            if (passwordVal.isEmpty()) {
                password.error = "Please enter password"
                password.requestFocus()
                return@setOnClickListener
            }
            if (phoneVal.isEmpty()) {
                phone.error = "Please enter phone number"
                phone.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.PHONE.matcher(phoneVal).matches()) {
                phone.error = "Please enter a valid phone number"
                phone.requestFocus()
                return@setOnClickListener
            }

            // If all validations pass, proceed with API call
            val api = "https://kbenkamotho.alwaysdata.net/api/signup"
            val data = RequestParams()
            data.put("username", nameVal)
            data.put("email", emailVal)
            data.put("password", passwordVal)
            data.put("phone", phoneVal)

            val helper = ApiHelper(applicationContext)
            helper.post(api, data)

            // Clear fields after sending
            email.text.clear()
            password.text.clear()
            phone.text.clear()
            username.text.clear()

            //intent to the main activity page
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
        }
    }
}
