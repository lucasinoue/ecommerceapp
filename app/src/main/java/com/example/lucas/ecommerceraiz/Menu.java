package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Produto;

import java.util.ArrayList;

/**
 * Created by Sidimar on 06/05/2017.
 */

public class Menu extends Activity{
    ListView listViewProdutos;
    Intent intent;
    ArrayAdapter <Produto> arrayAdapter;
    Button btNovoCliente;
    Button btNovoFornecedor;
    Button btNovoProduto;

    Parcelable[] parcelables;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        btNovoFornecedor = (Button) findViewById(R.id.btnNovoFornecedor);
        btNovoProduto = (Button) findViewById(R.id.btnNovoProduto);
        btNovoCliente = (Button) findViewById(R.id.btnNovoCliente);

        btNovoFornecedor.setOnClickListener(novoFornecedor);
        btNovoProduto.setOnClickListener(novoProduto);
        btNovoCliente.setOnClickListener(novoCliente);


        listViewProdutos = (ListView)findViewById(R.id.LVProdutos);
        intent = getIntent();
        if (intent.hasExtra("PRODUTOS")) {

            ArrayList<Produto> produto = getIntent().getParcelableArrayListExtra("PRODUTOS");
            arrayAdapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produto);
            listViewProdutos.setAdapter(arrayAdapter);

            }else {
            Toast.makeText(this, "Nao há produtos cadastrados", Toast.LENGTH_LONG);
            startActivity(new Intent(this, CadastroProduto.class));
        }
      //  arrayAdapter.setNotifyOnChange(true);


}

    View.OnClickListener novoFornecedor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(getApplicationContext(), com.example.lucas.ecommerceraiz.CadastroProduto.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener novoProduto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(getApplicationContext(), com.example.lucas.ecommerceraiz.CadastroProduto.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener novoCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(getApplicationContext(), com.example.lucas.ecommerceraiz.CadastroCliente.class);
            startActivity(intent);
            finish();
        }
    };


}
