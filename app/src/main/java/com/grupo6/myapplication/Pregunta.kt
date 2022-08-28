package com.grupo6.myapplication

import java.time.LocalDateTime

data class Pregunta(var titulo:String,var descripcion:String, var usuario:String, var fecha:String){
    constructor():this("","", "", "")


}
