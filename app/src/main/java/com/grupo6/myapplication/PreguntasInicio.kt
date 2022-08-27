package com.grupo6.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.grupo6.myapplication.databinding.ActivityInicioBinding
import com.grupo6.myapplication.databinding.ActivityLoginBinding
import com.grupo6.myapplication.databinding.FragmentPreguntasInicioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PreguntasInicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreguntasInicio : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var bttnPregunta: Button
    lateinit var layoutPegunta: LinearLayout
    lateinit var vista: View
    lateinit var titulo1: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        vista =  inflater.inflate(R.layout.fragment_preguntas_inicio, container, false)

        bttnPregunta = vista.findViewById(R.id.bttnAgregar)
        layoutPegunta = vista.findViewById(R.id.pregunta1)
        titulo1 = vista.findViewById(R.id.tituloPregunta1)

        consultarPreguntasRTDB()
        bttnPregunta.setOnClickListener(){
            val intencion = Intent(getActivity(), ActivityAnadirPregunta::class.java)
            startActivity(intencion)
        }

        layoutPegunta.setOnClickListener(){
            val intencion = Intent(getActivity(), VerPreguntaActivity::class.java)

            startActivity(intencion)
        }

        return vista;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PreguntasInicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PreguntasInicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun consultarPreguntasRTDB(){
        val database = Firebase.database.reference

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists() &&
                    dataSnapshot.child("preguntas").exists()&&
                    dataSnapshot.child("preguntas").childrenCount>0
                ){
                    var preguntas = ArrayList<Pregunta>()
                    for (pregunta  in dataSnapshot.child("preguntas").children){

                        preguntas.add(pregunta.getValue<Pregunta>() as Pregunta)
                    }
                    titulo1.text = preguntas[0].titulo

                    //Poblar en RecyclerView información usando mi adaptador
                    /*val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking);
                    recyclerViewRanking.layoutManager = LinearLayoutManager(this@RankingActivity);
                    recyclerViewRanking.adapter = RankingAdapter(usuarios);
                    recyclerViewRanking.setHasFixedSize(true);*/
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