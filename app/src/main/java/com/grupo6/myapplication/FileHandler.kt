package com.grupo6.myapplication

interface FileHandler {
    fun SaveInformation(datosAGrabar:Pair<String,String>)
    fun ReadInformation():Pair<String,String>
}