package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ActivityAnadirPregunta : AppCompatActivity() {
    lateinit var bttnDescartar: Button
    lateinit var  estadoEscribirPregunta: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_pregunta)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView5,FragmentEscribirPregunta()).commit()

        bttnDescartar = findViewById(R.id.buttonDescartarPregunta)
        bttnDescartar.setOnClickListener(){
            val intencion = Intent(this, Inicio::class.java)
            startActivity(intencion)
        }
    }
}