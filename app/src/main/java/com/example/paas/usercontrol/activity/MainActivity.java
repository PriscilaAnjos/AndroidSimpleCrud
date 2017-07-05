package com.example.paas.usercontrol.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paas.usercontrol.R;
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

        if(username.isEmpty() || password.isEmpty()){
            validationFail(getString(R.string.error_null_values_login));
        }else{
            checkAccess(username, password);
        }

    }

    // verifica o acesso do usuário
    private void checkAccess(String username, String password){

        ClienteController cliente = new ClienteController(getBaseContext());

        if(cliente.loginUser(username, password)){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("email", username);
            startActivity(intent);
        }else{
            validationFail(getString(R.string.error_not_access));
        }

    }

    private void validationFail(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
