package com.grupo6.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CanjeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CanjeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var puntosDisponiblesView: TextView
    lateinit var vista: View
    lateinit var bttnCanjear: Button
    private var puntos: Int=0






    override fun onCreate(savedInstanceState: Bundle?) {
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
        vista = inflater.inflate(R.layout.fragment_canje, container, false)
        puntosDisponiblesView=vista.findViewById(R.id.textViewDescripcion)
        consultarPuntosUsuario()


        bttnCanjear=vista.findViewById(R.id.btnCanjear3)
        bttnCanjear.setOnClickListener(){
            var puntosUno= getString(R.string.txtViewsPts2).toInt()
            if(puntos>=puntosUno){
                val dialogBuilder = AlertDialog.Builder(activity)
                dialogBuilder.setTitle("Confirmación de canje")
                dialogBuilder.setMessage("¿Esta seguro que desea canjear el producto?")
                dialogBuilder.setPositiveButton("Reclamar", DialogInterface.OnClickListener { _, _ ->
                    println("Reduccion de puntos")
                    Toast.makeText(activity,"Canje terminado satisfactoriamente",Toast.LENGTH_LONG).show()
                })
                dialogBuilder.setNegativeButton("Mejor no", DialogInterface.OnClickListener { dialog, which ->
                    //pass
                })
                dialogBuilder.create().show()

            }


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
         * @return A new instance of fragment CanjeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CanjeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun consultarPuntosUsuario(){
        val database = Firebase.database.reference
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists() &&
                    dataSnapshot.child("usuarios").exists()&&
                    dataSnapshot.child("usuarios").childrenCount>0
                ){
                    var preguntas = ArrayList<Pregunta>()
                    for (usuario  in dataSnapshot.child("usuarios").children){
                        var usuarioObtenido = usuario.getValue<Usuario>() as Usuario
                        println(usuarioObtenido.usuario)
                        if(usuarioObtenido.usuario== usuarioLogeado){
                            puntosDisponiblesView.setText(usuarioObtenido.puntos.toString())
                            puntos=usuarioObtenido.puntos
                            }

                        }
                    };


                }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(EXTRA_LOGIN, "loadPost:onCancelled", databaseError.toException())
            }
        }

        database.addValueEventListener(postListener)
    }
}