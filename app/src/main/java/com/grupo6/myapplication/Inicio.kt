package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Inicio : AppCompatActivity() {
    lateinit var bttnInicio:Button
    lateinit var  bttnMisPreguntas: Button
    lateinit var  bttnMisRespuestas: Button
    lateinit var bttnCanje: Button
    lateinit var  estadoInicio: ImageView
    lateinit var estadoMisPreguntas: ImageView
    lateinit var estadoMisRespuestas: ImageView
    lateinit var estadoCanje: ImageView
    lateinit var tvMiPerfil: TextView
    lateinit var logoP: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bttnInicio = findViewById(R.id.bttnInicio)
        bttnMisPreguntas = findViewById(R.id.bttnMisPreguntas)
        bttnMisRespuestas = findViewById(R.id.bttnMisRespuestas)
        bttnCanje = findViewById(R.id.bttnCanje)
        estadoInicio = findViewById(R.id.imgEstadoInicio)
        estadoMisPreguntas = findViewById(R.id.imgEstadoMisPreg)
        estadoMisRespuestas = findViewById(R.id.imgEstadoMisRes)
        estadoCanje = findViewById(R.id.imgEstadoCanje)
        tvMiPerfil = findViewById(R.id.miPerfil)
        logoP = findViewById(R.id.logoP)

        bttnInicio.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,PreguntasInicio()).commit()
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE
            estadoInicio.visibility = View.VISIBLE

        }

        logoP.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,PreguntasInicio()).commit()
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE
            estadoInicio.visibility = View.VISIBLE

        }

        bttnMisRespuestas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,MisRespuestas()).commit()
            estadoInicio.visibility = View.INVISIBLE
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE
            estadoMisRespuestas.visibility = View.VISIBLE

        }

        bttnMisPreguntas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,MisPreguntas()).commit()
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoInicio.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE
            estadoMisPreguntas.visibility = View.VISIBLE

        }

        bttnCanje.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,CanjeFragment()).commit()
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoInicio.visibility = View.INVISIBLE
            estadoCanje.visibility = View.VISIBLE

        }

        tvMiPerfil.setOnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)
            startActivity(intent)
        }

    }
}