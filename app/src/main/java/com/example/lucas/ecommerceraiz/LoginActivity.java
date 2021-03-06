package com.example.lucas.ecommerceraiz;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Usuario;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private ArrayList<Usuario> listaUsers;
    Intent intent;
    Button btLogin;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLogin = (Button) findViewById(R.id.btnLogin);
        btLogin.setOnClickListener(doLogin);
        //listaUsers = new ArrayList<Usuario>();
        //initializeArrayUsers();
        context = this;

    }

    private View.OnClickListener doLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login = (EditText) findViewById(R.id.etLogin);
            senha = (EditText) findViewById(R.id.etPassword);
            boolean loginOK;
            boolean passOK;

            Toast.makeText(getApplicationContext(),"teste botao", Toast.LENGTH_LONG).show();

            CadastroCliente cadastroCliente = new CadastroCliente();

            Cursor cursor = cadastroCliente.carregaDadoByEmail(login.getText().toString());
            if(!cursor.isNull(cursor.getColumnIndex(Banco.TBCliente.EMAIL))){

                Toast.makeText(getApplicationContext(),"login deu boa", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), Home_Cliente.class);
                intent.putExtra("DADOS", cursor.getString(cursor.getColumnIndex(Banco.TBCliente.NOME)));
            }else{
                Toast.makeText(getApplicationContext(),"Algo deu errado", Toast.LENGTH_LONG).show();
            }
          /*  for(Usuario user : listaUsers){
                Log.d("usuario", user.toString());
                loginOK = validaUsuario(user);
                passOK = validaSenha(user);

                if (loginOK && passOK) {
                    if (user.getUsuario().contains("admin")) {
                        Toast.makeText(getApplicationContext(), "OPa deu boa", Toast.LENGTH_LONG).show();
                        intent = new Intent(getApplicationContext(), com.example.lucas.ecommerceraiz.Menu.class);
                        startActivity(intent);
                        finish();

                    }else {
                        intent = new Intent(getApplicationContext(), CadastroCliente.class);
                        finish();
                    }
                    break;
                }else {
                    Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }


            }*/
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
