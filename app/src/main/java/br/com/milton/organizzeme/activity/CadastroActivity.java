package br.com.milton.organizzeme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.com.milton.organizzeme.R;
import br.com.milton.organizzeme.config.ConfiguracaoFirebase;
import br.com.milton.organizzeme.helper.Base64Custom;
import br.com.milton.organizzeme.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

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

                        usuario = new Usuario();
                        usuario.setNome(textoNome);
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        cadastrarUsuario();

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

    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, task -> {

            if ( task.isSuccessful() ){

                String idUsuario = Base64Custom.codificaBase64( usuario.getEmail() );
                usuario.setIdUsuario( idUsuario );
                usuario.salvar();
                finish();
                Toast.makeText(CadastroActivity.this,
                        "Cadastrado",
                        Toast.LENGTH_SHORT).show();

            }else {

                String excecao = "";
                try {
                    throw task.getException();
                }catch ( FirebaseAuthWeakPasswordException e){
                    excecao = "Digite uma senha mais forte!";
                }catch ( FirebaseAuthInvalidCredentialsException e){
                    excecao= "Por favor, digite um e-mail válido";
                }catch ( FirebaseAuthUserCollisionException e){
                    excecao = "Este conta já foi cadastrada";
                }catch (Exception e){
                    excecao = "Erro ao cadastrar usuário: "  + e.getMessage();
                    e.printStackTrace();
                }

                Toast.makeText(CadastroActivity.this,
                        excecao,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}