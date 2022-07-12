package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class VerPreguntaActivity : AppCompatActivity() {
    lateinit var bttnPublicar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pregunta)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView4,FragmentResponerPregunta()).commit()


    }
}