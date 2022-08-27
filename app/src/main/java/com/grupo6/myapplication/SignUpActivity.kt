package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo6.myapplication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnRegistrarse.setOnClickListener {
            val email =binding.etEmail.text.toString()
            val clave = binding.etContra.text.toString()
            if (binding.checkBoxAcuerdo.isChecked) {
                SignUpNewUser(email,clave)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                binding.checkBoxAcuerdo.error = "Acepte los términos y condciones"
                binding.checkBoxAcuerdo.requestFocus()
            }

        }

        binding.btnIngresar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun SignUpNewUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(EXTRA_LOGIN, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "New user saved.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(EXTRA_LOGIN, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

}