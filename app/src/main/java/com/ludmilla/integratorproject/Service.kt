package com.ludmilla.integratorproject

interface Service {
    @GET("characters")
    fun allCharacters(): Observable<Response>
}