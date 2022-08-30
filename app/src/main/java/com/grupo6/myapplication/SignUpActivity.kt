package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.grupo6.myapplication.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
        database = Firebase.database.reference
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarse.setOnClickListener {
            var email =binding.etEmail.text.toString()
            val clave = binding.etContra.text.toString()
            if(!ValidarDatosRequeridos())
                return@setOnClickListener
            if (binding.checkBoxAcuerdo.isChecked) {
                SignUpNewUser(email,clave)
                //crear usuario RTDB
                var usuarioCorreo = email.substringBefore("@")
                var usuarioRTDB = usuarioCorreo.replace(".","_")
                var usuario = Usuario(email,0,0,0,0,usuarioCorreo)
                database.child("usuarios").child(usuarioRTDB).setValue(usuario)
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

    private fun ValidarDatosRequeridos():Boolean {
        val email = binding.etEmail.text.toString()
        val clave = binding.etContra.text.toString()
        if (email.isEmpty()) {
            binding.etEmail.setError("El email es obligatorio")
            binding.etEmail.requestFocus()
            return false
        } else {
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            var esValido = pattern.matcher(email).matches()
            if (!esValido) {
                binding.etEmail.setError("El email no es correcto")
                binding.etEmail.requestFocus()
                return false
            }

        }
        if (clave.isEmpty()) {
            binding.etContra.setError("La clave es obligatoria")
            binding.etContra.requestFocus()
            return false

        }
        if (clave.length <= 7) {
            binding.etContra.setError("La clave debe tener al menos 8 caracteres")
            binding.etContra.requestFocus()
            return false
        }
        return true
    }

}