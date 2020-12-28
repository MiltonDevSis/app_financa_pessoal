package br.com.milton.organizzeme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.milton.organizzeme.model.Usuario;

public class UsuarioDAO implements IUsuarioDAO{

    private SQLiteDatabase insere;
    private SQLiteDatabase consulta;
    private Integer indice = 0;

    public UsuarioDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        insere = dbHelper.getWritableDatabase();
        consulta = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean cadastrar(Usuario usuario) {

        ContentValues cv = new ContentValues();

        cv.put("nome", usuario.getNome());
        cv.put("email", usuario.getEmail());
        cv.put("senha", usuario.getSenha());

        try {
            insere.insert(DBHelper.TABELA_USUARIOS, null, cv);
            Log.e("INFO", "Sucesso ao salvar");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Usuario usuario) {

        try {
            String[] args = {usuario.getId().toString()};
            insere.delete(DBHelper.TABELA_USUARIOS, "id=?", args);
            Log.i("INFO", "Sucesso ao deletar");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao deletar" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean alterar(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put("nome", usuario.getNome());
        cv.put("senha", usuario.getSenha());

        try {
            String[] args = {usuario.getId().toString()};
            insere.update(DBHelper.TABELA_USUARIOS, cv, "id=?", args);
            Log.i("INFO", "Sucesso ao atualizar");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao atualizar" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listarUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_USUARIOS ;

        Cursor c = consulta.rawQuery(sql, null);

        while (c.moveToNext()) {
            Usuario usuario = new Usuario();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String email = c.getString(c.getColumnIndex("email"));
            String senha = c.getString(c.getColumnIndex("senha"));

            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            listarUsuarios.add(usuario);
        }
        return listarUsuarios;
    }
}
