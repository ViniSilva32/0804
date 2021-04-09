package com.example.listapersonagem.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;

import static com.example.listapersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {
    //mostra na hud que o personagem está sendo editado
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    //mostra na hud que um novo personagem está sendo criado
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Persongem";
    //adiciona o nome
    private EditText campoNome;
    //adiciona a altura
    private EditText campoAltura;
    //adiciona o nascimento
    private EditText campoNascimento;
    //salva as informações inseridas pelo usuario
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //Adiciona o título
        inicilizacaoCampos();
        //deixa o botão salvar funcional
        configuraBotaoSalvar();
        //ao clicar no personagem, mostra as informações inseridas pelo usuario
        carregaPersonagem();

    }

    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            //altera a informação da hud (editando personagem)
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        } else {
            //altera a informação da hud (criando personagem)
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        //preenche os campos Nome, Altura, Nascimento
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotaoSalvar() {
        //adicionando ações no botão ao clicar nele
        Button botaoSalvar = findViewById(R.id.button_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Salvando as informações*/
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preenchePersonagem();
        if (personagem.idvalido()) {
            dao.edita(personagem);
        } else {
            //aplicando metodo salvar
            dao.salva(personagem);
        }
        finish();
    }

    private void inicilizacaoCampos() {
        //identificação dos campos criados
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }

    private void preenchePersonagem() {
        //guarda as informações inseridas pelo usuario
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}
