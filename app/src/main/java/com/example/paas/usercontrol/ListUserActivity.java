package com.example.paas.usercontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.paas.usercontrol.adapter.ClienteAdapter;
import com.example.paas.usercontrol.controller.ClienteController;
import com.example.paas.usercontrol.model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    /*
    * Lista os dados do cliente
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        // busca todos os clientes e seta na view por adapter
        ClienteController cliente = new ClienteController(getBaseContext());
        ArrayList<ClienteModel> listaCliente = cliente.listUser();

        ClienteAdapter clienteAdapter = new ClienteAdapter(this, listaCliente);
        ListView list = (ListView) findViewById(R.id.list_user);
        list.setAdapter(clienteAdapter);

    }

    // volta para a UserControlActivity
    public void goBack(View view) {
        Intent intent = new Intent(this, UserControlActivity.class);
        startActivity(intent);
    }
}
