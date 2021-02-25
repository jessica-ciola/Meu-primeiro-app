package com.ctt.primeiroapp

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.ctt.primeiroapp.model.Usuario
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val CICLO_VIDA = "CICLO_VIDA"
    private lateinit var botaoCadastrar : Button
    private lateinit var nomeUsuario : EditText
    private lateinit var idadeUsuario : EditText
    private lateinit var fotoUsuario : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(CICLO_VIDA, "App on OnCreate")

        nomeUsuario = findViewById(R.id.edtNomeUsuario)
        botaoCadastrar = findViewById(R.id.btnCadastrar)
        idadeUsuario = findViewById(R.id.edtIdade)
        fotoUsuario = findViewById(R.id.imgusuario)

        var contador = 0

        botaoCadastrar.setOnClickListener{

            val nomeDigitado = edtNomeUsuario.text.toString()
            val idadeDigitada = idadeUsuario.text.toString()


            if(nomeDigitado.isEmpty()){
                if(idadeDigitada.isEmpty()){
                    idadeUsuario.error = "Você ainda não nasceu?"
                } else{
                edtNomeUsuario.error = "Não existe nome vazio, né?"}
            }else{
                val usuario =  Usuario(++contador, nomeDigitado, idadeDigitada.toInt())
                exibirUsuario(usuario)

            }

        }

        fotoUsuario.setOnClickListener{
            abrirCamera()
        }

    }



    fun abrirCamera(){
        val CAMERA_REQUEST_CODE = 12345
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(cameraIntent.resolveActivity(packageManager) != null){
            //inicia a câmera
            startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE )
        }else{
            Toast.makeText(this,"Opa ... ALguma coisa deu errado! Tente novamente",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 12345 && resultCode == RESULT_OK) {
            val imagem = data?.extras?.get("data") as Bitmap
            fotoUsuario.setImageBitmap(imagem)
        }
    }


    fun exibirUsuario(usuario: Usuario){
        Toast.makeText(this," ${usuario.nome}, você foi cadastrado(a) com sucesso!",Toast.LENGTH_SHORT).show()
        redirecionar(usuario)
    }

    fun redirecionar(usuario: Usuario){


        //Intent(aonde eu estou, (CLASSE) para onde eu vou)
        //CUIDADO!  A classe precisa ser ::class.java pois a Intent pede uma (C)lass
        //Se pudesse, passar uma class KOTLIN, poderia chamar através ::class porém
        //O parâmetro precisaria ser do tipo (KC)lass

        val chaveNomeUsuario = "NOME"
        val chaveIdadeUsuario = "IDADE"
        val chaveImagem = "FOTO"


        val destinoActivity = Intent(this, UsuarioActivity::class.java)
        destinoActivity.putExtra(chaveNomeUsuario, usuario.nome)
        destinoActivity.putExtra(chaveIdadeUsuario, usuario.idade)


        //Inicia uma nova Activity
        startActivity(destinoActivity)

        //Encerra Activity ATUAL (MainActivity)

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

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this," Bye, Bye! Vamos sentir saudades!",Toast.LENGTH_SHORT).show()
        finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(CICLO_VIDA, "App on OnDestroy")
    }



}