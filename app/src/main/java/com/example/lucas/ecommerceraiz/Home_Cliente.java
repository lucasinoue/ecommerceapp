package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Sidimar on 25/06/2017.
 */

public class Home_Cliente extends Activity{

    Button bTCadastrar;
    Button btLogin;
    Button bTConfiguracoes;

    ListView listViewProdutos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_cliente);

        bTCadastrar = (Button) findViewById(R.id.btnSeCadastrar);
        btLogin = (Button) findViewById(R.id.btnLogin);
        bTConfiguracoes = (Button) findViewById(R.id.btnConfiguracoes);

        bTCadastrar.setOnClickListener(novoCadCliente);

        btLogin.setOnClickListener(login);
    }
    View.OnClickListener login = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener novoCadCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), CadastroCliente.class);
            startActivity(intent);
        }
    };
}
