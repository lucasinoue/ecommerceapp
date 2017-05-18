package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Cliente;

import java.util.ArrayList;

/**
 * Created by Sidimar on 05/05/2017.
 */

public class CadastroCliente extends Activity{
    protected EditText edNomeCliente;
    protected EditText edEmailCliente;
    protected EditText edSenhaCliente;

    protected Button btCadastrarCliente;

    protected ListView clientes;

    ArrayAdapter<Cliente> arrayAdapter;
    ArrayList<Cliente> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_cliente);

        edNomeCliente = (EditText) findViewById(R.id.edtNomeCliente);
        edEmailCliente = (EditText) findViewById(R.id.edtEmailCliente);
        edSenhaCliente = (EditText) findViewById(R.id.edtSenhaCliente);

        btCadastrarCliente = (Button) findViewById(R.id.btnCadCliente);

        clientes = (ListView) findViewById(R.id.lvClientes);

        Cliente cliente = new Cliente("jose","jose@jose.com", "jose");

        arrayList.add(cliente);

        arrayAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, arrayList);
        clientes.setAdapter(arrayAdapter);


        btCadastrarCliente.setOnClickListener(cadastrarCliente);

    }

    View.OnClickListener cadastrarCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cliente cliente = new Cliente(edNomeCliente.getText().toString(),edEmailCliente.getText().toString(),edSenhaCliente.getText().toString());
            //arrayList.add(cliente);
            arrayAdapter.add(cliente);
            arrayAdapter.setNotifyOnChange(true);
        }
    };
}
