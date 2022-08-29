package com.grupo6.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEscribirPregunta.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEscribirPregunta : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var vista: View
    lateinit var publicar: Button
    lateinit var tituloPregunta: EditText
    lateinit var descriptionPregunta: EditText
    private lateinit var database: DatabaseReference

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
        vista =  inflater.inflate(R.layout.fragment_escribir_pregunta, container, false)
        publicar = vista.findViewById(R.id.buttonPublicarPregunta)
        tituloPregunta = vista.findViewById(R.id.editTextTextTítuloPregunta)
        descriptionPregunta = vista.findViewById(R.id.editTextTextEscribirPregunta)
        database = Firebase.database.reference


        publicar.setOnClickListener {
            val preguntaNueva = Pregunta();
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = current.format(formatter)


            preguntaNueva.titulo = tituloPregunta.text.toString()
            preguntaNueva.descripcion = descriptionPregunta.text.toString()
            preguntaNueva.usuario = PreguntasInicio.GlobalVars.usuario
            preguntaNueva.fecha = formatted

            database.child("preguntas").child(preguntaNueva.idPregunta).setValue(preguntaNueva)

            val intent = Intent(getActivity(), Inicio::class.java)
            startActivity(intent)
        }
            return vista
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentEscribirPregunta.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentEscribirPregunta().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}