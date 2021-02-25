package com.ctt.primeiroapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val nomeUsuario = intent.extras?.get("NOME")
        val idadeUsuario = intent.extras?.get("IDADE")
        //val fotoDoUsuario = intent.extras?.get("FOTO")

        txtDadosUsuario.text = "Nome: $nomeUsuario \n" +  "Idade: $idadeUsuario anos"



    }


}