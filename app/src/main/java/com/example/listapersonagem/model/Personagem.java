package com.example.listapersonagem.model;

import androidx.annotation.NonNull;

public class Personagem {
    /*criação das variaveis locais e os parametros*/
    private final String nome;
    private final String altura;
    private final String nascimento;

    public Personagem(String nome, String altura, String nascimento) {


        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
    /*public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }

     */
}

