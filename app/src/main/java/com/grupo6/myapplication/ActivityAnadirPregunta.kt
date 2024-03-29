package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ActivityAnadirPregunta : AppCompatActivity() {
    lateinit var bttnDescartar: Button
    lateinit var tvMiPerfil: TextView
    lateinit var logoP: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_pregunta)
        tvMiPerfil = findViewById(R.id.miPerfil3)
        logoP = findViewById(R.id.logoP3)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView5,FragmentEscribirPregunta()).commit()

        bttnDescartar = findViewById(R.id.buttonDescartarPregunta)
        bttnDescartar.setOnClickListener(){
            val intencion = Intent(this, Inicio::class.java)
            startActivity(intencion)
        }


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