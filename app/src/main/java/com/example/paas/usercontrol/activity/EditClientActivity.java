package com.example.paas.usercontrol.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.paas.usercontrol.R;
import com.example.paas.usercontrol.controller.ClienteController;
import com.example.paas.usercontrol.model.ClienteModel;

import java.util.ArrayList;

public class EditClientActivity extends AppCompatActivity {

    /*
    * Edita os dados do cliente
    * */

    TextView nome;
    TextView sobrenome;
    TextView celular;
    TextView email;
    TextView senha;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        setObjects();

        Intent intent = getIntent();

        //pega os dados enviados por 'extra' do ClienteAdapter e seta na view
        if(intent.getExtras() != null){
            id = intent.getIntExtra("current_id", 0);
            System.out.print(id);
            ClienteController cliente = new ClienteController(getBaseContext());
            ArrayList<ClienteModel> clienteAtual = cliente.listOne(id);

            nome.setText(clienteAtual.get(0).getNome());
            sobrenome.setText(clienteAtual.get(0).getSobrenome());
            celular.setText(clienteAtual.get(0).getCelular());
            email.setText(clienteAtual.get(0).getEmail());
            senha.setText(clienteAtual.get(0).getSenha());

        }

    }

    //pegar os dados da view e envia para função de editar. Volta para a ListClientActivity
    public void editarUsuario(View view) {

        String nomeE = nome.getText().toString();
        String sobrenomeE = sobrenome.getText().toString();
        String celularE = celular.getText().toString();
        String emailE = email.getText().toString();
        String senhaE = senha.getText().toString();

        if(nomeE.isEmpty() || sobrenomeE.isEmpty() || celularE.isEmpty() || emailE.isEmpty() || senhaE.isEmpty()){
            validationFail(getString(R.string.error_null_values));
        }else{
            ClienteController cliente = new ClienteController(getBaseContext());
            cliente.editUser(id, nomeE, sobrenomeE, celularE, emailE, senhaE);

            // TODO: Tratar erro ao editar antes de trocar de activity

            Intent intent = new Intent(this, ListClientActivity.class);
            startActivity(intent);
        }


    }

    //volta para a ListClientActivity
    public void goBack(View view) {
        Intent intent = new Intent(this, ListClientActivity.class);
        startActivity(intent);
    }

    public void setObjects(){
        nome = (TextView) findViewById(R.id.edit_nome);
        sobrenome = (TextView) findViewById(R.id.edit_sobrenome);
        celular = (TextView) findViewById(R.id.edit_celular);
        email = (TextView) findViewById(R.id.edit_email);
        senha = (TextView) findViewById(R.id.edit_senha);
    }

    private void validationFail(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(EditClientActivity.this);
        builder.setTitle(getString(R.string.error_title));
        builder.setMessage(message);

        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
