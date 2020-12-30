package br.com.milton.organizzeme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import br.com.milton.organizzeme.R;
import br.com.milton.organizzeme.config.ConfiguracaoFirebase;
import br.com.milton.organizzeme.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText campoEmail, campoSenha;
    private boolean teste = false;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.edtEmailLogin);
        campoSenha = findViewById(R.id.edtSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {

            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();

            if (!email.isEmpty()){
                if (!senha.isEmpty()){

                    usuario = new Usuario();
                    usuario.setEmail( email );
                    usuario.setSenha( senha );
                    validarLogin();

                }else{
                    Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "Preencha o email!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(task -> {

            if ( task.isSuccessful() ){

                abreTelaPrincipal();

            }else {

                String excecao = "";
                try {
                    throw task.getException();
                }catch ( FirebaseAuthInvalidUserException e ) {
                    excecao = "Usuário não está cadastrado.";
                }catch ( FirebaseAuthInvalidCredentialsException e ){
                    excecao = "E-mail e senha não correspondem a um usuário cadastrado";
                }catch (Exception e){
                    excecao = "Erro ao cadastrar usuário: "  + e.getMessage();
                    e.printStackTrace();
                }

                Toast.makeText(LoginActivity.this,
                        excecao,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void abreTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}