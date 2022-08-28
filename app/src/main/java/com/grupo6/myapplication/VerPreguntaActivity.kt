package com.grupo6.myapplication

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class VerPreguntaActivity : AppCompatActivity() {
    lateinit var tvMiPerfil: TextView
    lateinit var logoP: ImageView
    lateinit var usuario: TextView
    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var fecha: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileName=intent. getStringExtra("usuario")
        setContentView(R.layout.activity_ver_pregunta)
        if (profileName != null) {
            consultarUsuarioRTDB(profileName)

        }
        tvMiPerfil = findViewById(R.id.miPerfil2)
        logoP = findViewById(R.id.logoP2)
        usuario = findViewById(R.id.textViewUsuario)
        titulo = findViewById(R.id.textViewTítulo)
        descripcion = findViewById(R.id.textViewCuerpoPregunta)
        fecha = findViewById(R.id.textViewFechaSubida)





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
            finish()
        }
    }

    fun consultarUsuarioRTDB(usuarioRequerido:String){
        val database = Firebase.database.reference
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists() &&
                    dataSnapshot.child("preguntas").exists()&&
                    dataSnapshot.child("preguntas").childrenCount>0
                ){

                    for (pregunta  in dataSnapshot.child("preguntas").children){
                        var preguntaObtenida = pregunta.getValue<Pregunta>()as Pregunta
                         if(preguntaObtenida.usuario == usuarioRequerido){
                             usuario.text = preguntaObtenida.usuario
                             titulo.text = preguntaObtenida.titulo
                             descripcion.text = preguntaObtenida.descripcion
                             fecha.text = preguntaObtenida.fecha

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