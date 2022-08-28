package com.grupo6.myapplication

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class PreguntasAdapter(private val dataSet: ArrayList<Pregunta>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val TYPE_HEADER : Int = 0
    class ViewHolderHeader(view : View) : RecyclerView.ViewHolder(view){

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitulo: TextView
        val textViewDescripcion: TextView
        val textViewUsuario: TextView
        val textViewFecha: TextView
        val linearLayoutPregunta: LinearLayout
        init {

            textViewTitulo = view.findViewById(R.id.textViewTitulo)
            textViewDescripcion = view.findViewById(R.id.textViewDescripcion)
            textViewUsuario = view.findViewById(R.id.recyclerViewTextViewUsuario)
            textViewFecha = view.findViewById(R.id.textViewFecha)
            linearLayoutPregunta = view.findViewById(R.id.linearLayoutPregunta)
        }

    }
    override fun getItemViewType(position: Int): Int {
        if(position == 0){
            return TYPE_HEADER
        }
        return 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER){
            val header = LayoutInflater.from(parent.context).inflate(R.layout.preguntas_vacio,parent,false)
            return ViewHolderHeader(header)
        }
        val header = LayoutInflater.from(parent.context).inflate(R.layout.preguntas_list,parent,false)
        return ViewHolder(header)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderHeader){

        }
        else if (holder is ViewHolder) {
            holder.textViewTitulo.text = dataSet[position-1].titulo
            holder.textViewDescripcion.text = dataSet[position-1].descripcion
            holder.textViewUsuario.text = dataSet[position-1].usuario
            holder.textViewFecha.text = dataSet[position-1].fecha

            holder.linearLayoutPregunta.setOnClickListener{

                val intencion = Intent(holder.textViewTitulo.context, VerPreguntaActivity::class.java)
                intencion.putExtra("usuario", holder.textViewUsuario.text)
                holder.textViewTitulo.context.startActivity(intencion)
            }

        }



    }
    override fun getItemCount() = dataSet.size + 1
}
