package com.nsierra.practica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    var operaciones: Operaciones? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operaciones = Operaciones()

        iniciarComponentes()
    }

    private val response =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { valor ->
            if (valor.resultCode == RESULT_OK) {
                //resp y resp 2 almacenan el mismo dato, solo se muestran 2 formas como podria capturarse el dato
                val resp = valor?.data?.extras?.get("resultado") as String
                val resp2 = valor?.data?.getStringExtra("resultado")
                println("Valor respuesta=$resp y la resps2=$resp2")
                //capturamos el objeto nuevo y lo asignamos a operaciones
                operaciones = valor?.data?.extras?.get("objetoOperaciones") as Operaciones?
                operaciones?.imprimirListaEstudiantes()
            }
        }

    private fun iniciarComponentes() {
        val registrar: Button = findViewById(R.id.btnRegistrar)
        registrar.setOnClickListener { onClick(1) }

        val estadisticas: Button = findViewById(R.id.btnEstadisticas)
        estadisticas.setOnClickListener { onClick(2) }

        val ayuda: Button = findViewById(R.id.btnAyuda)
        ayuda.setOnClickListener { onClick(3) }
    }

    private fun onClick(boton: Int) {
        when (boton) {
            1 -> {
                var miIntent: Intent = Intent(this, RegistroActivity::class.java)
                var miBundle: Bundle = Bundle()
                miBundle.putSerializable("operaciones", operaciones)
                miIntent.putExtras(miBundle)
                response.launch(miIntent)
            }

            2 -> {
                var miIntent: Intent = Intent(this, EstadisticasActivity::class.java)
                var miBundle: Bundle = Bundle()
                miBundle.putSerializable("operaciones", operaciones)
                miIntent.putExtras(miBundle)
                response.launch(miIntent)
            }

            3 -> {
                startActivity(Intent(this, AyudaActivity::class.java))
            }
        }
    }
}