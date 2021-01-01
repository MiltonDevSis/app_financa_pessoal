package br.com.milton.organizzeme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import br.com.milton.organizzeme.R;
import br.com.milton.organizzeme.helper.DateCustom;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoValor = findViewById(R.id.edtValor);
        campoData = findViewById(R.id.edtData);
        campoDescricao = findViewById(R.id.edtDescricao);
        campoCategoria = findViewById(R.id.edtCategoria);

        // preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());

    }
}