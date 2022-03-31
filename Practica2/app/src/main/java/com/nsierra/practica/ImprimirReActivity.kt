package com.nsierra.practica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ImprimirReActivity : AppCompatActivity() {
    var operaciones:Operaciones?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imprimir_reactivity)
        imprimir()
    }

    private fun imprimir(){
        val campoMensaje = findViewById<TextView>(R.id.textImprimir)
        var miBundle:Bundle?=this.intent.extras


        if(miBundle!=null){
            campoMensaje.text = "${miBundle.getSerializable("est")}}, ${miBundle.getString("mensaje")}"
        }
    }

}