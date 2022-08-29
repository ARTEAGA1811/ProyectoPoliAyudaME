package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class UsuarioActivity : AppCompatActivity() {
    lateinit var logoP: ImageView
    lateinit var usuarioPerfil: TextView
    lateinit var puntos: TextView
    lateinit var preguntasHechas: TextView
    lateinit var preguntasContestadas: TextView
    lateinit var premiosObtenidos: TextView
    lateinit var email : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        usuarioPerfil = findViewById(R.id.textViewPerfilUsuario)
        logoP = findViewById(R.id.logoP4)
        puntos = findViewById(R.id.textViewPerfilPuntos)
        preguntasHechas = findViewById(R.id.textViewPerfilPreguntasHechas)
        preguntasContestadas = findViewById(R.id.textViewPerfilPreguntasContestadas)
        premiosObtenidos = findViewById(R.id.textViewPerfilPremiosObtenidos)
        email = findViewById(R .id.textViewPerfilEmail)
        consultarUsuarioRTDB(usuarioLogeado)

        logoP.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }
    fun consultarUsuarioRTDB(usuarioRequerido:String){
        val database = Firebase.database.reference
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists() &&
                    dataSnapshot.child("usuarios").exists()&&
                    dataSnapshot.child("usuarios").childrenCount>0
                ){

                    for (usuario  in dataSnapshot.child("usuarios").children){
                        var usuarioObtenido = usuario.getValue<Usuario>()as Usuario
                        if(usuarioObtenido.usuario == usuarioRequerido ){

                            usuarioPerfil.text = usuarioObtenido.usuario
                            email.text = usuarioObtenido.email
                            puntos.text = usuarioObtenido.puntos.toString()
                            preguntasContestadas.text = usuarioObtenido.preguntasContestadas.toString()
                            preguntasHechas.text = usuarioObtenido.preguntasHechas.toString()
                            premiosObtenidos.text = usuarioObtenido.premiosObtenidos.toString()
                            break


                        }
                    }


                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(EXTRA_LOGIN, "loadPost:onCancelled", databaseError.toException())
            }
        }

        database.addValueEventListener(postListener)

    }
}