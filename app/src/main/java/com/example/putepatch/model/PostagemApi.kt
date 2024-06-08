package com.example.putepatch.model

import com.example.putepatch.service.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostagemApi {


    @PUT("posts/{id}")
    suspend fun atualizarPostagemPut(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ):Response<Postagem>


    @PATCH("posts/{id}")
    suspend fun atualizarPostagemPatch(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ):Response<Postagem>


    @DELETE("posts/{id}")
    suspend fun deletarPostagem(
        @Path("id") id: Int
    ):Response<Unit>

}