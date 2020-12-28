package br.com.milton.organizzeme.model;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario " +
                "id = " + id +
                ", \nnome = '" + nome + '\'' +
                ", \nemail = '" + email + '\'' +
                ", \nsenha = '" + senha;
    }
}
