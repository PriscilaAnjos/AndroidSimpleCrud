package com.example.paas.usercontrol.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.paas.usercontrol.dao.ClienteDAO;
import com.example.paas.usercontrol.model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paas on 03/07/2017.
 */

public class ClienteController {

    /*
    * Controller para gerenciar as requisições das Activities
    * - Valida as informações
    * */

    Context contextClient;
    ClienteDAO cliente;

    public ClienteController(Context context){
        contextClient = context;
        cliente = new ClienteDAO(context);
    }

    public boolean newClient(String nome, String sobrenome, String celular, String email, String senha){
        if(!(nome.isEmpty() || senha.isEmpty() || celular.isEmpty() || email.isEmpty() || senha.isEmpty())){
            boolean novoCliente = cliente.insereDado(nome, sobrenome, celular, email, senha);
            if(novoCliente){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean loginUser(String nome, String senha){

        boolean access;

        if(!(nome.isEmpty() || senha.isEmpty())){
            access = cliente.checkLogin(nome, senha);
        }else {
            access = false;
        }

        return access;
    }

    public ArrayList<ClienteModel> listUser(){
        return cliente.getUsers();
    }

    public ArrayList<ClienteModel> listOne(Integer id){

        //  TODO: Verificação de dados de entrada válidos
        return cliente.getOne(id);
    }

    public void deleteUser(Integer id){
        //  TODO: Verificação de dados de entrada válidos
        cliente.deleteDado(id);
    }

    public void editUser(int id, String nome, String sobrenome, String celular, String email, String senha){
        //  TODO: Verificação de dados de entrada válidos
        cliente.alteraDados(id, nome, sobrenome, celular, email, senha);
    }

}
