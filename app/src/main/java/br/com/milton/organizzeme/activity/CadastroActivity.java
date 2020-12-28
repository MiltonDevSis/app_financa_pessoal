package br.com.milton.organizzeme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.milton.organizzeme.R;
import br.com.milton.organizzeme.database.UsuarioDAO;
import br.com.milton.organizzeme.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText campoNome, campoEmail, campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.edtNome);
        campoEmail = findViewById(R.id.edtEmail);
        campoSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {

            String textoNome  = campoNome.getText().toString();
            String textoEmail = campoEmail.getText().toString();
            String textoSenha = campoSenha.getText().toString();

            if (!textoNome.isEmpty()){
                if (!textoEmail.isEmpty()){
                    if (!textoSenha.isEmpty()){



                        Usuario usuario = new Usuario();
                        usuario.setNome(textoNome);
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);

                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                        usuarioDAO.cadastrar( usuario );
                        limparCampos();
                        Toast.makeText(CadastroActivity.this, "Sucesso ao cadastar " + textoNome, Toast.LENGTH_LONG).show();
                        listarCadastrosBanco();

                    }else {
                        Toast.makeText(CadastroActivity.this, "Preencha o senha!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(CadastroActivity.this, "Preencha o email!", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void limparCampos(){
        campoNome.setText("");
        campoEmail.setText("");
        campoSenha.setText("");
    }

    public void listarCadastrosBanco(){

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        List<Usuario> lista = usuarioDAO.listar();
        System.out.println(lista + "\n");
    }

}