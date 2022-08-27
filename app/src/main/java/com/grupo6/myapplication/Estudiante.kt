package com.grupo6.myapplication

data class Estudiante(var usuario:String, var preguntas:Array<String>){
    constructor():this("",arrayOf())
}
