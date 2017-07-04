package com.example.paas.usercontrol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paas.usercontrol.util.Constants;

/**
 * Created by paas on 03/07/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    /*
    * Criação do banco de dados e tabelas
    * - Tabela criada -> cliente
    * */
    public DatabaseManager(Context context) {
        super(context, Constants.NOME_BANCO, null, Constants.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Constants.CLIENT_TABELA +" ("
                + Constants.CLIENT_ID + " integer primary key autoincrement, "
                + Constants.CLIENT_NOME + " text, "
                + Constants.CLIENT_SOBRENOME + " text, "
                + Constants.CLIENT_CELULAR + " text, "
                + Constants.CLIENT_EMAIL + " text, "
                + Constants.CLIENT_SENHA + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Constants.CLIENT_TABELA);
        onCreate(db);
    }
}
