package com.example.listapersonagem.dao;

import com.example.listapersonagem.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    // criação do indice para salvar personagens
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;
    
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.SetId(contadorDeId);
        personagens.add(personagemSalvo);
        contadorDeId++;
        
    }

    public Personagem edita (Personagem personagem){
        Personagem personagemEscolhido = null;
        for (Personagem p:
            personagens) {
            if(p.getId() == personagem.getId()){
                personagemEscolhido = p;
            }

        }
        if(personagemEscolhido != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
        return personagemEscolhido;
    }

    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }

    public void remove(Personagem personagem) {
       Personagem personagemDevolvido = edita(personagem);
       personagens.remove(personagemDevolvido);
    }
}
