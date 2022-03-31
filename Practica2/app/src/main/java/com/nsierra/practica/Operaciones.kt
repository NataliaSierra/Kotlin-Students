package com.nsierra.practica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import java.io.Serializable


class Operaciones : Serializable{
    var listaEstudiantes: ArrayList<Estudiante> =arrayListOf<Estudiante>()

    fun registrarEstudiante(estudiante: Estudiante){
        listaEstudiantes.add(estudiante)
    }

    fun imprimirListaEstudiantes(){
        for(est in listaEstudiantes){
            println(est)
        }
        println(listaEstudiantes.get(0).nombre)
    }

    fun calcularPromedio(est: Estudiante): Double {

        var prom=(est.nota1+est.nota2+est.nota3+est.nota4+est.nota5)/5

        if(prom < 3.5){

        }

        return prom
    }
}