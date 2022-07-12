package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo6.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aquí empiezo con el binging
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Cambio de pantalla a la pantalla de registro
        binding.btnRegistrarse.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //CAmbio a la pantalla principal
        binding.btnIniciarSesion.setOnClickListener{
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }
}