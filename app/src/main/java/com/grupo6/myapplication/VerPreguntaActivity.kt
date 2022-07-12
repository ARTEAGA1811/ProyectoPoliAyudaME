package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class VerPreguntaActivity : AppCompatActivity() {
    lateinit var tvMiPerfil: TextView
    lateinit var logoP: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pregunta)
        tvMiPerfil = findViewById(R.id.miPerfil2)
        logoP = findViewById(R.id.logoP2)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView4,FragmentResponerPregunta()).commit()

        tvMiPerfil.setOnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)
            startActivity(intent)
        }

        logoP.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }
}