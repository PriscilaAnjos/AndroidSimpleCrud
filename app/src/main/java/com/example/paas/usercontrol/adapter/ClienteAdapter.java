package com.example.paas.usercontrol.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.paas.usercontrol.activity.EditClientActivity;
import com.example.paas.usercontrol.R;
import com.example.paas.usercontrol.controller.ClienteController;
import com.example.paas.usercontrol.model.ClienteModel;

import java.util.ArrayList;

/**
 * Created by paas on 03/07/2017.
 */

public class ClienteAdapter extends ArrayAdapter<ClienteModel> {

    /*
    * Adapter criado para tratar a exibição de Clientes na listagem
    * - Seta os valores para serem exibidos
    * - Botão para deletar -> chama o controller para deletar o usuário
    * - Botão editar -> muda para a 'EditClientActivity' mandando o id como um 'extra'
    * */
    ClienteController cliente = new ClienteController(getContext());

    public ClienteAdapter(Activity context, ArrayList<ClienteModel> clienteModels) {
            super(context, 0, clienteModels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_client_item, parent, false);
        }

        ClienteModel currentClient = getItem(position);

        TextView id = (TextView) listItemView.findViewById(R.id.list_id);
        id.setText(getContext().getString(R.string.id) + "\t\t" + currentClient.getId());

        TextView nome = (TextView) listItemView.findViewById(R.id.list_nome);
        nome.setText(getContext().getString(R.string.nome) + "\t\t" + currentClient.getNome());

        TextView sobrenome = (TextView) listItemView.findViewById(R.id.list_sobrenome);
        sobrenome.setText(getContext().getString(R.string.sobrenome) + "\t\t" + currentClient.getSobrenome());

        TextView celular = (TextView) listItemView.findViewById(R.id.list_celular);
        celular.setText(getContext().getString(R.string.celular) + "\t\t" + currentClient.getCelular());

        TextView email = (TextView) listItemView.findViewById(R.id.list_email);
        email.setText(getContext().getString(R.string.email) + "\t\t" + currentClient.getEmail());

        final Integer current_id = Integer.parseInt(currentClient.getId());

        Button btnEdit = (Button) listItemView.findViewById(R.id.list_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditClientActivity.class);
                intent.putExtra("current_id", current_id);
                getContext().startActivity(intent);
            }
        });

        Button btnDelete = (Button) listItemView.findViewById(R.id.list_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente.deleteUser(current_id);

                cliente.listUser();
            }
        });

        return listItemView;

    }

}
