package com.example.paas.usercontrol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paas.usercontrol.R;
import com.example.paas.usercontrol.adapter.ClienteAdapter;
import com.example.paas.usercontrol.controller.ClienteController;
import com.example.paas.usercontrol.model.ClienteModel;

import java.util.ArrayList;

public class ListClientActivity extends AppCompatActivity {

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
        // TODO: verificar retorno vazio e tratar esse retorno na view

        if(listaCliente.size() < 0){
            ((TextView) findViewById(R.id.return_null)).setText(getString(R.string.error_null_return));
        }else{
            ClienteAdapter clienteAdapter = new ClienteAdapter(this, listaCliente);
            ListView list = (ListView) findViewById(R.id.list_user);
            list.setAdapter(clienteAdapter);
        }

    }

    // volta para a HomeActivity
    public void goBack(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
