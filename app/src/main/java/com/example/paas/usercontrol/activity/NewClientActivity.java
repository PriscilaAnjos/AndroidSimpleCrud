package com.example.paas.usercontrol.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.paas.usercontrol.R;
import com.example.paas.usercontrol.controller.ClienteController;

public class NewClientActivity extends AppCompatActivity {

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

        if(nome.isEmpty() || sobrenome.isEmpty() || celular.isEmpty() || email.isEmpty() || senha.isEmpty()){
            validationFail(getString(R.string.error_null_values));
        }else{
            ClienteController cliente = new ClienteController(getBaseContext());

            cliente.newClient(nome, sobrenome, celular, email, senha);

            Intent intentExtra = getIntent();
            if(intentExtra.getExtras() != null){
                String extra = intentExtra.getExtras().getString("intent_name");

                Intent intent;
                if(extra.equals(HomeActivity.class.getSimpleName())){
                    intent = new Intent(this, HomeActivity.class);
                }else{
                    intent = new Intent(this, MainActivity.class);
                }
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void validationFail(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(NewClientActivity.this);
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
