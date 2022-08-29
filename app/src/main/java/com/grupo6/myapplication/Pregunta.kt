package com.grupo6.myapplication

import java.time.LocalDateTime
var idContadorPregunta: Int = 0
data class Pregunta(var idPregunta: String, var titulo:String,var descripcion:String, var usuario:String, var fecha:String){

    constructor():this("pregunta" + (calculoPreguntas().toString()),"","", "", "")

}

fun calculoPreguntas(): Int {
    PreguntasInicio.GlobalVars.numPreguntas += 1
    return PreguntasInicio.GlobalVars.numPreguntas
}

