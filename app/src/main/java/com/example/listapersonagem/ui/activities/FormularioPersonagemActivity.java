package com.example.listapersonagem.ui.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        setTitle("Formulário de Personagens");

        PersonagemDAO dao = new PersonagemDAO();

        EditText campoNome = findViewById(R.id.edittext_nome);
        EditText campoAltura = findViewById(R.id.edittext_nome);
        EditText campoNascimento = findViewById(R.id.edittext_nome);

        Button botaoSalvar = findViewById(R.id.button_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Salvando as informações*/
            public void onClick(View view){


                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);

                new Personagem(nome, altura, nascimento);

                startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

               // Toast.makeText(FormularioPersonagemActivity.this,
                 //       personagemSalvo.getNome() + " - " +
                   //             personagemSalvo.getAltura() + " - " +
                     //           personagemSalvo.getNascimento(), Toast.LENGTH_SHORT);

                //Toast.makeText(FormularioPersonagemActivity.this,"Estou funcionando!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
