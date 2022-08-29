package com.grupo6.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentResponerPregunta.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentResponerPregunta : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var vista: View
    lateinit var publicar: Button
    lateinit var tituloRespuesta: EditText
    lateinit var descriptionRespuesta: EditText
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
        vista =  inflater.inflate(R.layout.fragment_responer_pregunta, container, false)
        publicar = vista.findViewById(R.id.buttonPublicarRespuesta)
        tituloRespuesta = vista.findViewById(R.id.editTextTextTítuloRespuesta)
        descriptionRespuesta = vista.findViewById(R.id.editTextDescripcionRespuesta)
        database = Firebase.database.reference


        publicar.setOnClickListener{
            if(!ValidarDatosRequeridos())
                return@setOnClickListener

            val respuestaNueva = Respuesta();
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = current.format(formatter)

            respuestaNueva.idPregunta = PreguntasInicio.GlobalVars.idPregunta2
            respuestaNueva.titulo = tituloRespuesta.text.toString()
            respuestaNueva.descripcion = descriptionRespuesta.text.toString()
            respuestaNueva.usuario = PreguntasInicio.GlobalVars.usuario
            respuestaNueva.fecha = formatted

            database.child("respuestas").child(respuestaNueva.idRespuesta).setValue(respuestaNueva)

            val intent = Intent(getActivity(), Inicio::class.java)
            startActivity(intent)

        }

        return vista
    }
    private fun ValidarDatosRequeridos():Boolean {
        val titulo = tituloRespuesta.text.toString()
        val descripcion = descriptionRespuesta.text.toString()
        if (titulo.isEmpty()) {
            tituloRespuesta.setError("El título es obligatorio")
            tituloRespuesta.requestFocus()
            return false
        }


        if (descripcion.isEmpty()) {
            descriptionRespuesta.setError("La descripcion es obligatoria")
            descriptionRespuesta.requestFocus()
            return false

        }
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentResponerPregunta.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentResponerPregunta().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}