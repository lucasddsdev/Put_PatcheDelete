package com.example.putepatch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.putepatch.databinding.ActivityMainBinding
import com.example.putepatch.model.PostagemApi
import com.example.putepatch.service.Postagem
import com.example.putepatch.service.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView( binding.root)

        binding.btnIniciar.setOnClickListener(){


            CoroutineScope(Dispatchers.IO).launch {
                atualizarPostagem()
            }

        }
    }


    private suspend fun atualizarPostagem(){

        var retorno: Response<Unit>? = null



        try {

            val postagemApi = retrofit.create(PostagemApi::class.java)
            retorno = postagemApi.deletarPostagem(1)



        } catch (e: Exception) {

            e.printStackTrace()

        }

        var resultado = ""

        if (retorno != null){
            if (retorno.isSuccessful){

                resultado = "Sucesso ao remover postagem"
            }
        }

        withContext(Dispatchers.Main){
            binding.textTexto.setText(resultado)
        }


    }
}