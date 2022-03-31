package com.nsierra.practica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EstadisticasActivity : AppCompatActivity() {
    var operaciones:Operaciones? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        validar()
    }

    private fun validar(){
        var bundle: Bundle? = this.intent.extras
        operaciones = bundle?.getSerializable("operaciones") as Operaciones?

        var prom = operaciones?.listaEstudiantes?.get(0)?.promedio?.toDouble()
        var length = operaciones?.listaEstudiantes?.size

        val nR = findViewById<TextView>(R.id.textNR)
        nR.text = length.toString()

        val nG = findViewById<TextView>(R.id.textNG)

        var i = 0
        for(est in operaciones?.listaEstudiantes!!){
            if(est.promedio > 3.4){
                i += 1
            }
        }

        nG.text = i.toString()

        val nP = findViewById<TextView>(R.id.textNP)

        var k = 0
        for(est in operaciones?.listaEstudiantes!!){
            if(est.promedio < 3.5){
                k +=1
            }
        }

        nP.text = k.toString()

        val nRe = findViewById<TextView>(R.id.textNRE)

        var l = 0
        for(est in operaciones?.listaEstudiantes!!){
            if(est.promedio > 2.4 && est.promedio < 3.5){
                l +=1
            }
        }

        nRe.text = l.toString()
    }
}