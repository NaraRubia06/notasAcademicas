package com.example.notasacademicas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextIdade;
    private EditText editTextDisciplina;
    private EditText editTextNota1;
    private EditText editTextNota2;
    private Button buttonEnviar;
    private Button buttonLimpar;
    private TextView textViewResultado;
    private TextView textViewErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);
        textViewResultado = findViewById(R.id.textViewResultado);
        textViewErro = findViewById(R.id.textViewErro);
        buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonLimpar = findViewById(R.id.buttonLimpar);

            // botão Enviar
            buttonEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validarEExibirDados();
                }
            });

            // botão Limpar
            buttonLimpar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    limparCampos();
                }
            });
        }

    private void validarEExibirDados() {

        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String idade = editTextIdade.getText().toString();
        String disciplina = editTextDisciplina.getText().toString();
        String nota1 = editTextNota1.getText().toString();
        String nota2 = editTextNota2.getText().toString();

        // Validar os campos que o usuario vai inserir os dados
        if (nome.isEmpty()) {
            textViewErro.setText("O campo de nome está vazio");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        } else if (email.isEmpty() || !email.contains("@")) {
            textViewErro.setText("O email é inválido");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        } else if (idade.isEmpty() || Integer.parseInt(idade) <= 0) {
            textViewErro.setText("A idade deve ser um número positivo");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        } else if (nota1.isEmpty() || nota2.isEmpty() || !isNotaValida(nota1) || !isNotaValida(nota2)) {
            textViewErro.setText("As notas devem ser entre 0 e 10");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        // Se todos os dados estiverem validados, vai calcular a média
        double media = (Double.parseDouble(nota1) + Double.parseDouble(nota2)) / 2;

        // Exibe os dados
        textViewResultado.setText("Nome: " + nome + "\nEmail: " + email + "\nIdade: " + idade +
                "\nDisciplina: " + disciplina + "\nNotas: " + nota1 + " e " + nota2 +
                "\nMédia: " + media +
                "\n" + (media >= 6 ? "Aprovado" : "Reprovado"));

        // Altera a cor do texto com base na média
        if (media < 6) {
            textViewResultado.setTextColor(getResources().getColor(android.R.color.holo_red_dark));

        } else {
            textViewResultado.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

        }
        textViewResultado.setVisibility(View.VISIBLE);
        textViewErro.setVisibility(View.GONE);

    }

    // Método para verificar se a nota do aluno e valida entre 0 a 10
    private boolean isNotaValida(String nota) {
        try {
            double valor = Double.parseDouble(nota);
            return valor >= 0 && valor <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para limpar os campos e a mensagens
    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewResultado.setVisibility(View.GONE);
        textViewErro.setVisibility(View.GONE);
    }
}

