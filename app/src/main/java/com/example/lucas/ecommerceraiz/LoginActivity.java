package com.example.lucas.ecommerceraiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Usuario;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private ArrayList<Usuario> listaUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaUsers = new ArrayList<Usuario>();
        initializeArrayUsers();

    }

    private View.OnClickListener doLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login = (EditText) findViewById(R.id.etLogin);
            senha = (EditText) findViewById(R.id.etPassword);
            boolean loginOK;
            boolean passOK;

            Intent intent;
            for(Usuario user : listaUsers){

                loginOK = validaUsuario(user);
                passOK = validaSenha(user);

                if (loginOK && passOK) {
                    Toast.makeText(getApplicationContext(), "OPa deu boa", Toast.LENGTH_LONG).show();
                    intent = new Intent(getApplication(),Menu.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
                    intent = new Intent(getApplicationContext(),CadastroCliente.class);
                    startActivity(intent);
                }


            }
        }
    };


    private void initializeArrayUsers() {
        Usuario admin = new Usuario("admin", "12345");
        Usuario cliente = new Usuario("cliente", "45678");
        this.listaUsers.add(admin);
        this.listaUsers.add(cliente);
    }

    public boolean validaUsuario(Usuario user) {
        if(user.getUsuario() != null && user.getUsuario().contains(login.getText().toString())) {
            return true;
        }
        return false;
    }

    public boolean validaSenha(Usuario user) {
        if(user.getSenha() != null && user.getSenha().contains(senha.getText().toString())) {
            return true;
        }
        return false;
    }

}
