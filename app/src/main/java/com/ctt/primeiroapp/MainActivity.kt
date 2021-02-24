package com.ctt.primeiroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ctt.primeiroapp.model.Usuario
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val CICLO_VIDA = "CICLO_VIDA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(CICLO_VIDA, "App on OnCreate")

        val mensagem = "Usuario cadastrado com sucesso!"
        val botao = findViewById<Button>(R.id.btnCadastrar)
        val idadeUsuario = findViewById<EditText>(R.id.edtIdade)

        var contador = 0

        btnCadastrar.setOnClickListener{
            val nomeDigitado = edtNomeUsuario.text.toString()
            val idadeDigitada = idadeUsuario.text.toString()


            if(nomeDigitado.isEmpty()){
                if(idadeDigitada.isEmpty()){
                    idadeUsuario.error = "Não existe idade vazia, né?"
                } else{
                edtNomeUsuario.error = "Não existe nome vazio, né?"}
            }else{
                val usuario =  Usuario(++contador, nomeDigitado, idadeDigitada.toInt())
                exibirUsuario(usuario)
            }

        }




    }

    fun exibirMensagem(mensagem:String){
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show()
    }

    fun exibirUsuario(usuario: Usuario){
        Log.e("USUARIO", usuario.toString())
        Toast.makeText(this,"Bem vindo ${usuario.nome} , seu id é ${usuario.id}",Toast.LENGTH_SHORT).show()
    }


    override fun onStart() {
        super.onStart()
        Log.e(CICLO_VIDA, "App on OnStop")
    }

    override fun onResume() {
        super.onResume()
        Log.e(CICLO_VIDA, "App on OnResume")
    }

    override fun onStop() {
        super.onStop()
        Log.e(CICLO_VIDA, "App on OnResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(CICLO_VIDA, "App on OnDestroy")
    }



}