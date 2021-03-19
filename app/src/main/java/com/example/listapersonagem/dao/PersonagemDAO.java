package com.example.listapersonagem.dao;

import com.example.listapersonagem.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    // criação do indice para salvar personagens
    private final static List<Personagem> personagens = new ArrayList<>();
    
    public void salva(Personagem personagemSalvo) {
        
        personagens.add(personagemSalvo);
        
    }

    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
