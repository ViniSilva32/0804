package com.example.listapersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.listapersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // torna a o nome da lista mutável
        setTitle(TITULO_APPBAR);
        //codigo refatorado
        configuraFabNovoPersonagem();
        configuraLista();
    }

    //configurações para tornar o botão de adicionar novos nomes a lista funcional
    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioSalva();
            }
        });
    }
    //Volta para o formulario
    private void abreFormularioSalva() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void remove(Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
        remove(personagemEscolhido);
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        //mostra a lista de personagens
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        configuraAdapter(listaDePersonagens);
        configuraItemPorClique(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //selecão de personagem
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                //retorna ao formulario
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }
    //torna possivel abrir e editar os dados do personagem
    private void abreFormularioModoEditar(Personagem personagem) {
        Intent indoParaFormulario = new Intent(this, FormularioPersonagemActivity.class);
        indoParaFormulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(indoParaFormulario);
    }

    private void configuraAdapter(ListView listaDePersonagens) {
        //linha responsável por acumular os personagens na lista
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);

    }


}
