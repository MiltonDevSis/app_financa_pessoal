package br.com.milton.organizzeme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import br.com.milton.organizzeme.R;
import br.com.milton.organizzeme.helper.DateCustom;
import br.com.milton.organizzeme.model.Movimentacao;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;

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

    public void salvarDespesa(View view){

        movimentacao = new Movimentacao();
        String data = campoData.getText().toString();
        movimentacao.setData( data );
        movimentacao.setCategoria(campoCategoria.getText().toString());
        movimentacao.setDescricao(campoDescricao.getText().toString());
        movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
        movimentacao.setTipo("d");

        movimentacao.salvar(data);

    }
}