package br.com.milton.organizzeme.database;

import java.util.List;

import br.com.milton.organizzeme.model.Usuario;

public interface IUsuarioDAO {

    boolean cadastrar(Usuario usuario);
    boolean deletar(Usuario usuario);
    boolean alterar(Usuario usuario);
    List<Usuario> listar();
}
