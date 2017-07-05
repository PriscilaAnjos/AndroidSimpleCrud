package com.example.paas.usercontrol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.paas.usercontrol.R;

public class HomeActivity extends AppCompatActivity {

    /*
    * Troca a activity para ver os clientes ou cadastrar um novo cliente
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_control);
    }

    public void getUser(View view) {
        Intent intent = new Intent(this, ListClientActivity.class);
        startActivity(intent);
    }

    public void newUser(View view) {
        Intent intent = new Intent(this, NewClientActivity.class);
        intent.putExtra("intent_name", HomeActivity.class.getSimpleName());
        startActivity(intent);
    }

    public void getOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
