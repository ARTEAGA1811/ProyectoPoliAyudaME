package com.grupo6.myapplication

data class Usuario(var usuario:String, var email:String, var puntos:Int, var preguntasContestadas:Int,var preguntasHechas:Int,var premiosObtenidos:Int){
    constructor():this("","",0,0,0,0)
}
