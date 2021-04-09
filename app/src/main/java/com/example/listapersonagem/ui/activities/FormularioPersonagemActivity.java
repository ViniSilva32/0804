package com.example.listapersonagem.ui.activities;


import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem Personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //Adiciona o título
        setTitle("Formulário de Personagens");
        inicilizacaoCampos();
        configuraBotao();

        Intent dados = getIntent();
        if(dados.hasExtra("personagem")) {
            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
            campoNome.setText(personagem.getNome());
            campoAltura.setText(personagem.getAltura());
            campoNascimento.setText(personagem.getNascimento());
        } else {
            Personagem = new Personagem();
        }


    }

    private void configuraBotao() {
        //adicionando ações no botão ao clicar nele
        Button botaoSalvar = findViewById(R.id.button_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Salvando as informações*/
            public void onClick(View view){

                //converte os textos em strings
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                //aplicando metodo salvar
                dao.salva(personagemSalvo);
                finish();

                //startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                //new Personagem(nome, altura, nascimento);

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.edita(personagemSalvo);

               // Toast.makeText(FormularioPersonagemActivity.this,
                 //       personagemSalvo.getNome() + " - " +
                   //             personagemSalvo.getAltura() + " - " +
                     //           personagemSalvo.getNascimento(), Toast.LENGTH_SHORT);

                //Toast.makeText(FormularioPersonagemActivity.this,"Estou funcionando!",Toast.LENGTH_SHORT).show();



            }
        });
    }

    private void inicilizacaoCampos() {
        //idenctificação dos campos criados
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }

}
