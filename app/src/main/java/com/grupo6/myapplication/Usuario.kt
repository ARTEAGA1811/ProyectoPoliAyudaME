package com.grupo6.myapplication

data class Usuario(var email: String, var preguntasContestadas:Int,var preguntasHechas:Int, var premiosObtenidos:Int, var puntos:Int, var usuario:String){
    constructor():this("",0,0, 0, 0,"")
}
