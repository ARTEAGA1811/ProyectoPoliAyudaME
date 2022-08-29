package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class VerPreguntaActivity : AppCompatActivity() {
    lateinit var tvMiPerfil: TextView
    lateinit var logoP: ImageView
    lateinit var usuario: TextView
    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var fecha: TextView
    lateinit var publicar: Button
    private lateinit var database: DatabaseReference
    var  idPregunta = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileName = intent. getStringExtra("usuario")
        val tituloPregunta = intent. getStringExtra("titulo")
        val idPregunta = intent. getStringExtra("idPregunta")
        var esRespuesta = intent.getStringExtra("esRespuesta")

        setContentView(R.layout.activity_ver_pregunta)
        if(esRespuesta == "true"){
            println(esRespuesta)
            println(idPregunta)
            if (idPregunta != null) {
                consultarPreguntaRTDB(idPregunta)
            }
        }else{
            println(esRespuesta)
            if (profileName != null && tituloPregunta != null) {
                consultarUsuarioRTDB(profileName,tituloPregunta)

            }
        }

        tvMiPerfil = findViewById(R.id.miPerfil2)
        logoP = findViewById(R.id.logoP2)
        usuario = findViewById(R.id.textViewUsuario)
        titulo = findViewById(R.id.textViewTítulo)
        descripcion = findViewById(R.id.textViewCuerpoPregunta)
        fecha = findViewById(R.id.textViewFechaSubida)



        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerViewRespuesta,FragmentResponerPregunta()).commit()

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


    fun consultarUsuarioRTDB(usuarioRequerido:String, tituloPregunta:String){
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
                         if(preguntaObtenida.usuario == usuarioRequerido && preguntaObtenida.titulo == tituloPregunta){
                             PreguntasInicio.GlobalVars.idPregunta2 = preguntaObtenida.idPregunta
                             usuario.text = preguntaObtenida.usuario
                             titulo.text = preguntaObtenida.titulo
                             descripcion.text = preguntaObtenida.descripcion
                             fecha.text = preguntaObtenida.fecha
                             consultarRespuestasRTDB(pregunta.key.toString())
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
    fun consultarPreguntaRTDB(idPreguntaRespuesta: String){
        val database = Firebase.database.reference
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists() &&
                    dataSnapshot.child("preguntas").exists()&&
                    dataSnapshot.child("preguntas").childrenCount>0
                ){

                    for (pregunta  in dataSnapshot.child("preguntas").children){
                        var idPregunta = pregunta.key
                        println(idPregunta)
                        var preguntaObtenida = pregunta.getValue<Pregunta>() as Pregunta
                        if (idPregunta != null) {
                            preguntaObtenida.idPregunta = idPregunta
                        }

                        if(preguntaObtenida.idPregunta == idPreguntaRespuesta ){
                            PreguntasInicio.GlobalVars.idPregunta2 = preguntaObtenida.idPregunta
                            usuario.text = preguntaObtenida.usuario
                            titulo.text = preguntaObtenida.titulo
                            descripcion.text = preguntaObtenida.descripcion
                            fecha.text = preguntaObtenida.fecha
                            consultarRespuestasRTDB(pregunta.key.toString())
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

    fun consultarRespuestasRTDB(idPregunta:String){
        val database = Firebase.database.reference
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists() &&
                    dataSnapshot.child("respuestas").exists()&&
                    dataSnapshot.child("respuestas").childrenCount>0
                ){
                    var respuestas  = ArrayList<Respuesta>()
                    for (respuesta  in dataSnapshot.child("respuestas").children){
                        respuestas.add(respuesta.getValue<Respuesta>()as Respuesta)
                    }
                    PreguntasInicio.GlobalVars.numRespuestas = respuestas.size
                    println(PreguntasInicio.GlobalVars.numRespuestas)
                    var respuestasFilradas  = ArrayList<Respuesta>()
                    for(respuesta in respuestas){
                        if(respuesta.idPregunta == idPregunta){

                            respuestasFilradas.add(respuesta )
                        }
                    }
                    //Poblar en RecyclerView información usando mi adaptador
                    val recyclerViewRespuestas: RecyclerView = findViewById(R.id.recyclerViewRespuestas);
                    recyclerViewRespuestas.layoutManager = LinearLayoutManager(this@VerPreguntaActivity);
                    recyclerViewRespuestas.adapter = RespuestasAdapter(respuestasFilradas);
                    recyclerViewRespuestas.setHasFixedSize(true);


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