package com.example.listapersonagem.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    /*criação das variaveis locais e os parametros*/
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;
    // salva as informações preenchidas pelo usuario.
    public Personagem(String nome, String altura, String nascimento) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Personagem() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    // transformando as informações para string
    @NonNull
    @Override
    //informações a serem colocadas na lista
    public String toString() {
        return nome;
    }

   public void SetId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    /*public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }*/

}

