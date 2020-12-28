package br.com.milton.organizzeme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.milton.organizzeme.R;
import br.com.milton.organizzeme.controller.UsuarioController;
import br.com.milton.organizzeme.database.UsuarioDAO;
import br.com.milton.organizzeme.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText campoEmail, campoSenha;
    private boolean teste = false;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioController = UsuarioController.getInstance(getApplicationContext());

        campoEmail = findViewById(R.id.edtEmailLogin);
        campoSenha = findViewById(R.id.edtSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {

            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();

            if (!email.isEmpty()){
                if (!senha.isEmpty()){

                        validaLogin(email, senha);

                }else{
                    Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "Preencha o email!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void validaLogin(String email, String senha) {

        try {
            boolean isValid = usuarioController.validaLogin(email, senha);

            if (isValid) { // se true
                abreTelaPrincipal();
            } else {       // se false
                Toast.makeText(LoginActivity.this, "Verifique usuario e senha!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Erro validando usuario e senha", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void abreTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}