package com.example.sokogarden

import ApiHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Find the two edit texts, a button and a textview by use of their id's
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signinbutton = findViewById<Button>(R.id.signinBtn)
        val signuptextview = findViewById<TextView>(R.id.signuptxt)

        //On the text view, wet onclick listener such that it navigates me to the signup page
        signuptextview.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
        //onclick of the button, signin, we need to interact with the api end points as we pass the two data info, that is the email and password
        signinbutton.setOnClickListener {
            //specify the api end points
            val api = "https://kbenkamotho.alwaysdata.net/api/signin"

            //Create a request parram that enables you to hold the data in form of a bundle/package
            val data = RequestParams()
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

            //Instantiate the ApiHelper class
            val helper = ApiHelper(this)
            //By use of post_login inside the function helper class, post your data
            helper.post_login(api, data)

            // Clear the input fields after clicking the button
            email.text.clear()
            password.text.clear()

            //Research on shareprefenreces in android

        }




    }
}
