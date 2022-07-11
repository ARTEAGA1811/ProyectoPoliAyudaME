package com.grupo6.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class Inicio : AppCompatActivity() {
    lateinit var bttnInicio:Button
    lateinit var  bttnMisPreguntas: Button
    lateinit var  bttnMisRespuestas: Button
    lateinit var bttnCanje: Button
    lateinit var  estadoInicio: ImageView
    lateinit var estadoMisPreguntas: ImageView
    lateinit var estadoMisRespuestas: ImageView
    lateinit var estadoCanje: ImageView
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
        bttnInicio.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,PreguntasInicio()).commit()
            estadoInicio.visibility = View.VISIBLE
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE


        }

        bttnMisRespuestas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,MisRespuestas()).commit()
            estadoMisRespuestas.visibility = View.VISIBLE
            estadoInicio.visibility = View.INVISIBLE
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE



        }
        bttnMisPreguntas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,MisPreguntas()).commit()
            estadoMisPreguntas.visibility = View.VISIBLE
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoInicio.visibility = View.INVISIBLE
            estadoCanje.visibility = View.INVISIBLE
        }

        /*bttnCanje.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,Canje()).commit()
            estadoCanje.visibility = View.VISIBLE
            estadoMisPreguntas.visibility = View.INVISIBLE
            estadoMisRespuestas.visibility = View.INVISIBLE
            estadoInicio.visibility = View.INVISIBLE
        }*/

    }
}