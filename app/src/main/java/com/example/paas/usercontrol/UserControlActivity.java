package com.example.paas.usercontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.paas.usercontrol.controller.ClienteController;

import java.util.List;

public class UserControlActivity extends AppCompatActivity {

    /*
    * Troca a activity para ver os clientes ou cadastrar um novo cliente
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_control);
    }

    public void getUser(View view) {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }

    public void newUser(View view) {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }
}
