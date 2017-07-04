package com.example.paas.usercontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paas.usercontrol.controller.ClienteController;

public class MainActivity extends AppCompatActivity {

    /*
    * Activity principal, inicial --> Login
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // pega os dados dos inputs e envia para a função checkAccess
    public void loginClick(View view) {

        String username = ((EditText) findViewById(R.id.login_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_password)).getText().toString();

        checkAccess(username, password);

    }

    // verifica o acesso do usuário
    private void checkAccess(String username, String password){

        ClienteController cliente = new ClienteController(getBaseContext());

        if(cliente.loginUser(username, password)){
            Intent intent = new Intent(this, UserControlActivity.class);
            intent.putExtra("email", username);
            startActivity(intent);
        }else{
            Toast.makeText(this, getString(R.string.error_access), Toast.LENGTH_LONG).show();
        }

    }

}
