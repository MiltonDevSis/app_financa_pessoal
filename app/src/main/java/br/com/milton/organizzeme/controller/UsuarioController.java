package br.com.milton.organizzeme.controller;

import android.content.Context;

import java.util.List;

import br.com.milton.organizzeme.database.UsuarioDAO;
import br.com.milton.organizzeme.model.Usuario;

public class UsuarioController {

    private static UsuarioDAO usuarioDAO;
    private static UsuarioController instance;

    public static UsuarioController getInstance(Context context) {

        if (instance == null) {
            instance = new UsuarioController();
            usuarioDAO = new UsuarioDAO(context);
        }
        return instance;
    }

    public void cadastrar(Usuario usuario) throws Exception {
        usuarioDAO.cadastrar(usuario);
    }

    public void alterar(Usuario usuario) throws Exception {
        usuarioDAO.alterar(usuario);
    }

    public List<Usuario> listar() throws Exception {
        return usuarioDAO.listar();
    }

    public boolean validaLogin(String email, String senha){

        Usuario usuario = usuarioDAO.findByLogin(email, senha);

        if (usuario == null || usuario.getEmail() == null || usuario.getSenha() == null) {
            return false;
        }

        String informado = email + senha;
        String esperado = usuario.getEmail() + usuario.getSenha();

        if (informado.equals(esperado)) {
            return true;
        }
        return false;
    }
}
