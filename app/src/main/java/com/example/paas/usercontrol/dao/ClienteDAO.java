package com.example.paas.usercontrol.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.paas.usercontrol.database.DatabaseManager;
import com.example.paas.usercontrol.model.ClienteModel;
import com.example.paas.usercontrol.util.Constants;

import java.util.ArrayList;

/**
 * Created by paas on 03/07/2017.
 */

public class ClienteDAO {

    /*
    * Classe de conexão com o banco de dados
    * */

    private static SQLiteDatabase db;
    private static DatabaseManager banco;
    private static final String LOG_TAG = ClienteDAO.class.getSimpleName();

    public ClienteDAO(Context context){
        banco = new DatabaseManager(context);
    }

    // Verifica a existencia do cliente para o Login
    public static boolean checkLogin(String nome, String senha){
        Cursor cursor;
        String[] campos =  {Constants.CLIENT_SENHA, Constants.CLIENT_NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(Constants.CLIENT_TABELA, campos, Constants.CLIENT_NOME + "= '"+ nome + "' AND " + Constants.CLIENT_SENHA + " = '" + senha + "'", null, null, null, null, null);
        boolean access = false;

        if(cursor.moveToFirst()){
            do{
                String nomeBanco = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_NOME));
                String senhaBanco = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_SENHA));

                if(nome.equals(nomeBanco) && senha.equals(senhaBanco)){
                    access = true;
                }else{
                    access = false;
                }
            }while (cursor.moveToNext());
        }
        db.close();
        return access;
    }

    // Busca todos os clientes
    public static ArrayList<ClienteModel> getUsers(){

        Cursor cursor;
        ArrayList<ClienteModel> usuarios = new ArrayList<>();
        String[] campos =  {Constants.CLIENT_ID, Constants.CLIENT_NOME, Constants.CLIENT_SOBRENOME, Constants.CLIENT_CELULAR, Constants.CLIENT_EMAIL, Constants.CLIENT_SENHA};
        db = banco.getReadableDatabase();
        cursor = db.query(Constants.CLIENT_TABELA, campos, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_ID));
                String nome = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_NOME));
                String sobrenome = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_SOBRENOME));
                String celular = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_CELULAR));
                String email = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_EMAIL));
                String senha = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_SENHA));

                ClienteModel cliente = new ClienteModel();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setCelular(celular);
                cliente.setEmail(email);
                cliente.setSenha(senha);

                usuarios.add(cliente);

                System.out.println(usuarios);
            }while (cursor.moveToNext());
        }
        db.close();
        return usuarios;
    }

    // Busca um único cliente pelo id
    public static ArrayList<ClienteModel> getOne(Integer idClient){

        Cursor cursor;
        ArrayList<ClienteModel> usuario = new ArrayList<>();
        String[] campos =  {Constants.CLIENT_NOME, Constants.CLIENT_SOBRENOME, Constants.CLIENT_CELULAR, Constants.CLIENT_EMAIL, Constants.CLIENT_SENHA};
        db = banco.getReadableDatabase();
        cursor = db.query(Constants.CLIENT_TABELA, campos, Constants.CLIENT_ID + "= '" + idClient + "'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                String nome = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_NOME));
                String sobrenome = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_SOBRENOME));
                String celular = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_CELULAR));
                String email = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_EMAIL));
                String senha = cursor.getString(cursor.getColumnIndex(Constants.CLIENT_SENHA));

                ClienteModel cliente = new ClienteModel();
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setCelular(celular);
                cliente.setEmail(email);
                cliente.setSenha(senha);

                usuario.add(cliente);

            }while (cursor.moveToNext());
        }
        db.close();
        return usuario;
    }

    // insere um novo cliente
    public static boolean insereDado(String nome, String sobrenome, String celular, String email, String senha){
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(Constants.CLIENT_NOME, nome);
        values.put(Constants.CLIENT_SOBRENOME, sobrenome);
        values.put(Constants.CLIENT_CELULAR, celular);
        values.put(Constants.CLIENT_EMAIL, email);
        values.put(Constants.CLIENT_SENHA, senha);

        resultado = db.insert(Constants.CLIENT_TABELA, null, values);
        db.close();

        if (resultado ==-1)
            return true;
        else
            return false;

    }

    // edita dados do cliente por id
    public void alteraDados(int id, String nome, String sobrenome, String celular, String email, String senha){
        ContentValues valuesEdit;

        db = banco.getWritableDatabase();

        valuesEdit = new ContentValues();
        valuesEdit.put(Constants.CLIENT_NOME, nome);
        valuesEdit.put(Constants.CLIENT_SOBRENOME, sobrenome);
        valuesEdit.put(Constants.CLIENT_CELULAR, celular);
        valuesEdit.put(Constants.CLIENT_EMAIL, email);
        valuesEdit.put(Constants.CLIENT_SENHA, senha);

        db.update(Constants.CLIENT_TABELA, valuesEdit, Constants.CLIENT_ID + "= '" + id + "'", null);
        db.close();
    }

    // deleta um cliente por id
    public void deleteDado(int id){
        db = banco.getReadableDatabase();
        db.delete(Constants.CLIENT_TABELA,Constants.CLIENT_ID + "=" + id,null);
        db.close();
    }
}
