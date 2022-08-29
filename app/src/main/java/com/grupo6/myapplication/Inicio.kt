package com.grupo6.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
    lateinit var bttnMate: Button
    lateinit var bttnFisica: Button
    lateinit var bttnQuimica: Button
    lateinit var bttnGeo: Button
    lateinit var bttnTodo: Button
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
        bttnFisica = findViewById(R.id.Física)
        bttnQuimica = findViewById(R.id.Química)
        bttnGeo = findViewById(R.id.Geometría)
        bttnMate = findViewById(R.id.Matemática)
        bttnTodo = findViewById(R.id.todo)

        pintarBoton()

        val extras = intent.extras ?: return
        var usuario = extras.getString(EXTRA_LOGIN) ?:"Unknown"
        usuario = usuario.substringBefore("@")
        PreguntasInicio.GlobalVars.usuario = usuario

    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
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



        bttnTodo.setOnClickListener{
            PreguntasInicio.GlobalVars.filtraMateria = false
            reiniciarActividad()
        }

        bttnGeo.setOnClickListener {
            PreguntasInicio.GlobalVars.filtraMateria = true
            PreguntasInicio.GlobalVars.Materia = "Geometría"
            reiniciarActividad()
        }

        bttnMate.setOnClickListener {
            PreguntasInicio.GlobalVars.filtraMateria = true
            PreguntasInicio.GlobalVars.Materia = "Matemática"
            reiniciarActividad()
        }

        bttnQuimica.setOnClickListener {
            PreguntasInicio.GlobalVars.filtraMateria = true
            PreguntasInicio.GlobalVars.Materia = "Química"
            reiniciarActividad()
        }

        bttnFisica.setOnClickListener {
            PreguntasInicio.GlobalVars.filtraMateria = true
            PreguntasInicio.GlobalVars.Materia = "Física"
            reiniciarActividad()
        }


    }
    fun reiniciarActividad(){
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("ResourceAsColor")
    fun reiniciarBotones(){
        bttnTodo.setBackgroundResource(R.drawable.botones_blancos)
        bttnTodo.setTextColor(R.color.Boton)
        bttnGeo.setBackgroundResource(R.drawable.botones_blancos)
        bttnGeo.setTextColor(R.color.Boton)
        bttnQuimica.setBackgroundResource(R.drawable.botones_blancos)
        bttnQuimica.setTextColor(R.color.Boton)
        bttnFisica.setBackgroundResource(R.drawable.botones_blancos)
        bttnFisica.setTextColor(R.color.Boton)
        bttnMate.setBackgroundResource(R.drawable.botones_blancos)
        bttnMate.setTextColor(R.color.Boton)
    }

    @SuppressLint("ResourceAsColor")
    fun pintarBoton(){
        reiniciarBotones()
        if(PreguntasInicio.GlobalVars.filtraMateria){
            when (PreguntasInicio.GlobalVars.Materia){
                "Geometría" -> {
                    bttnGeo.setBackgroundResource(R.drawable.botones)
                    bttnGeo.setTextColor(R.color.white)
                }
                "Matemática" -> {
                    bttnMate.setBackgroundResource(R.drawable.botones)
                    bttnMate.setTextColor(R.color.white)
                }
                "Química" -> {
                    bttnQuimica.setBackgroundResource(R.drawable.botones)
                    bttnQuimica.setTextColor(R.color.white)
                }
                "Física" -> {
                    bttnFisica.setBackgroundResource(R.drawable.botones)
                    bttnFisica.setTextColor(R.color.white)
                }

            }
        }else{
            bttnTodo.setBackgroundResource(R.drawable.botones)
            bttnTodo.setTextColor(R.color.white)
        }
    }

}