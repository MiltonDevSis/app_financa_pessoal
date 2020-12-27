package br.com.milton.organizzeme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_motoristas";
    public static String TABELA_MOTORISTAS = "motoristas";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_MOTORISTAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, email VARCHAR (100) not null, " +
                "telefone VARCHAR (20) not null, senha VARCHAR (20) NOT NULL ,nota REAL, latitude REAL, longitude REAL); ";
        try {
            db.execSQL( sql );
            Log.i("Info DB", "Sucesso ao criar a tabela: " + TABELA_MOTORISTAS);
        }catch (Exception e){
            Log.i("Info DB", "Erro ao criar a tabela: " + TABELA_MOTORISTAS + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_MOTORISTAS + " ;";

        try {
            db.execSQL( sql );
            onCreate( db );
            Log.i("Info DB", "Sucesso ao atualizar o app");
        }catch (Exception e){
            Log.i("Info DB", "Erro ao atualizar o app" + e.getMessage());
        }
    }
}
