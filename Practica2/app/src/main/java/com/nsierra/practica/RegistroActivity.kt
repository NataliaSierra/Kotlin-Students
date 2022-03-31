package com.nsierra.practica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistroActivity : AppCompatActivity() {

    var campoDocumento: EditText? = null
    var campoNombre: EditText? = null
    var campoEdad: EditText? = null
    var campoTelefono: EditText? = null
    var campoDireccion: EditText? = null

    var campoMateria1: EditText? = null
    var campoMateria2: EditText? = null
    var campoMateria3: EditText? = null
    var campoMateria4: EditText? = null
    var campoMateria5: EditText? = null
    var campoNota1: EditText? = null
    var campoNota2: EditText? = null
    var campoNota3: EditText? = null
    var campoNota4: EditText? = null
    var campoNota5: EditText? = null

    var operaciones: Operaciones? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        iniciarComponentes()
    }

    private fun iniciarComponentes() {
        operaciones = Operaciones()

        campoDocumento = findViewById(R.id.editTextDocumento)
        campoNombre = findViewById(R.id.editTextNombre)
        campoEdad = findViewById(R.id.editTextEdad)
        campoTelefono = findViewById(R.id.editTextTelefono)
        campoDireccion = findViewById(R.id.editTextDirrecion)

        campoMateria1 = findViewById(R.id.editTextMateria1)
        campoMateria2 = findViewById(R.id.editTextMateria2)
        campoMateria3 = findViewById(R.id.editTextMateria3)
        campoMateria4 = findViewById(R.id.editTextMateria4)
        campoMateria5 = findViewById(R.id.editTextMateria5)

        campoNota1 = findViewById(R.id.editTextNota1)
        campoNota2 = findViewById(R.id.editTextNota2)
        campoNota3 = findViewById(R.id.editTextNota3)
        campoNota4 = findViewById(R.id.editTextNota4)
        campoNota5 = findViewById(R.id.editTextNota5)

        val registrar: Button = findViewById(R.id.btnRegistro)
        registrar.setOnClickListener { validarDatos() }

        var bundle: Bundle? = this.intent.extras
        operaciones = bundle?.getSerializable("operaciones") as Operaciones?
        campoDocumento = findViewById(R.id.editTextDocumento)
        campoNombre = findViewById(R.id.editTextNombre)
        campoEdad = findViewById(R.id.editTextEdad)


    }

    private fun validarDatos() {

        operaciones = Operaciones()
        var campoPrimeraNota = findViewById<EditText>(R.id.editTextNota1).text.toString()
        var campoSegundaNota = findViewById<EditText>(R.id.editTextNota2).text.toString()
        var campoTerceraNota = findViewById<EditText>(R.id.editTextNota3).text.toString()
        var campoCuartaNota = findViewById<EditText>(R.id.editTextNota4).text.toString()
        var campoQuintaNota = findViewById<EditText>(R.id.editTextNota5).text.toString()

        var notaUno: Double = campoPrimeraNota.toDouble()
        var notaDos: Double = campoSegundaNota.toDouble()
        var notaTres: Double = campoTerceraNota.toDouble()
        var notaCuatro: Double = campoCuartaNota.toDouble()
        var notaCinco: Double = campoQuintaNota.toDouble()
        // Validaciones numeros entre 0 y 5

        var pasa = 0
        if (notaUno > 5 || notaUno < 0) {
            pasa = 1
            campoNota1?.setError(null)
            campoNota1?.error = "Escriba un numero entre 0 y 5"
        }

        if (notaDos > 5 || notaDos < 0) {
            pasa = 1
            campoNota2?.setError(null)
            campoNota2?.error = "scriba un numero entre 0 y 5"
        }

        if (notaTres > 5 || notaTres < 0) {
            pasa = 1
            campoNota3?.setError(null)
            campoNota3?.error = "scriba un numero entre 0 y 5"
        }

        if (notaCuatro > 5 || notaCuatro < 0) {
            pasa = 1
            campoNota4?.setError(null)
            campoNota4?.error = "scriba un numero entre 0 y 5"
        }

        if (notaCinco > 5 || notaCinco < 0) {
            pasa = 1
            campoNota5?.setError(null)
            campoNota5?.error = "scriba un numero entre 0 y 5"
        }
        if (pasa == 1){

        }else{
            registrarEstudiante()
        }
    }


    private fun registrarEstudiante() {
        var est: Estudiante = Estudiante()
        est.documento = campoDocumento?.text.toString()
        est.nombre = campoNombre?.text.toString()
        est.edad = campoEdad?.text.toString().toInt()
        est.direccion = campoDireccion?.text.toString()
        est.telefono = campoTelefono?.text.toString()

        est.materia1 = campoMateria1?.text.toString()
        est.materia2 = campoMateria2?.text.toString()
        est.materia3 = campoMateria3?.text.toString()
        est.materia4 = campoMateria4?.text.toString()
        est.materia5 = campoMateria5?.text.toString()

        est.nota1 = campoNota1?.text.toString().toDouble()
        est.nota2 = campoNota2?.text.toString().toDouble()
        est.nota3 = campoNota3?.text.toString().toDouble()
        est.nota4 = campoNota4?.text.toString().toDouble()
        est.nota5 = campoNota5?.text.toString().toDouble()

        est.promedio = operaciones!!.calcularPromedio(est)

        operaciones?.registrarEstudiante(est)
        operaciones?.imprimirListaEstudiantes()

        var mensajes = ""
        if (est.promedio >= 3.5) {
            mensajes = "Usted ganó el periodo"
        } else if (est.promedio > 2.5 && est.promedio < 3.5) {
            mensajes = "Usted perdió el periodo pero puede recuperar"
        } else {
            mensajes = "Usted perdió el periodo"
        }
        est.resultado=mensajes

        intent = Intent(this, ImprimirReActivity::class.java)
        val miBundle:Bundle=Bundle()
        miBundle.putSerializable("est", est)
        miBundle.putString("mensaje", mensajes)
        intent.putExtras(miBundle)
        startActivity(intent)
    }

    private fun devolverResultados(){
        var miIntent: Intent = Intent()
        miIntent.putExtra("resultado","Registro exitoso")
        var miBundle:Bundle= Bundle()
        miBundle.putSerializable("objetoOperaciones",operaciones)
        miIntent.putExtras(miBundle)
        //miIntent.putExtra("obj",operaciones)
        setResult(RESULT_OK,miIntent)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "Se cierra el registro Activity", Toast.LENGTH_SHORT).show()
            devolverResultados()
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}