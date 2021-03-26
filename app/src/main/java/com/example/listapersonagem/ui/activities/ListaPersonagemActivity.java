package com.example.listapersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity
{
    private final PersonagemDAO dao = new PersonagemDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // torna a o nome da lista mutável
        setTitle("Lista de Personagens");

        //instancia personagens pré prontos
        dao.salva(new Personagem("Ken","1,80","02041979"));
        dao.salva(new Personagem("Ryu","1,80","02041979"));

        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex","Ken","Ryu", "Chun-li"));

       /* TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);

        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        //acessar os dados dos personagens adicionados a lista
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagens = dao.todos();
        //adicionando personagens
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //selecão de personagem
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao);
                //Log.i("personagem",""+ personagemEscolhido);
                //retorna ao formulario
                Intent indoParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                indoParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(indoParaFormulario);
            }
        });
    }
}
