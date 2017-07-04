package com.example.paas.usercontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.paas.usercontrol.controller.ClienteController;

public class NewUserActivity extends AppCompatActivity {

    /*
    * Cadastra um novo usuário
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void cadastrarUsuario(View view) {

        String nome = ((EditText) findViewById(R.id.new_nome)).getText().toString();
        String sobrenome = ((EditText) findViewById(R.id.new_sobrenome)).getText().toString();
        String celular = ((EditText) findViewById(R.id.new_celular)).getText().toString();
        String email = ((EditText) findViewById(R.id.new_email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.new_senha)).getText().toString();

        ClienteController cliente = new ClienteController(getBaseContext());

        cliente.newClient(nome, sobrenome, celular, email, senha);

        Intent intent = new Intent(this, UserControlActivity.class);
        startActivity(intent);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, UserControlActivity.class);
        startActivity(intent);
    }
}
