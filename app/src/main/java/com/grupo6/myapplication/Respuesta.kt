package com.grupo6.myapplication

import java.time.LocalDateTime
var idContadorRespuesta: Int = 0
data class Respuesta( var idRespuesta:String, var titulo:String,var descripcion:String, var usuario:String, var fecha:String, var idPregunta: String){

    constructor():this(idContadorRespuesta++.toString(),"","", "", "", ""    )

}
