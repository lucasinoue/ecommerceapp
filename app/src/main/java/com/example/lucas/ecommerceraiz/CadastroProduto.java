package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Produto;

import java.util.ArrayList;

/**
 * Created by Sidimar on 05/05/2017.
 */



public class CadastroProduto extends Activity{

    public ArrayList<Produto> arrayProduto;
    public Intent intent;
    Button bcadastrar;
    EditText eTMarca;
    EditText eTModelo;
    EditText eTValor;
    Spinner spFornecedor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produto);

        bcadastrar = (Button) findViewById(R.id.btnCadProduto);
        eTMarca = (EditText) findViewById(R.id.edtMarca) ;
        eTModelo = (EditText) findViewById(R.id.edtModelo);
        eTValor = (EditText) findViewById(R.id.edtValor);
        spFornecedor = (Spinner) findViewById(R.id.spinerFornecedor);

        bcadastrar.setOnClickListener(cadastrarProduto);
        arrayProduto = new ArrayList<Produto>();

        arrayProduto.add(new Produto("SECULOS", "ouze azul", 389.35, "joao"));
        arrayProduto.add(new Produto("DUMONT", "SLIM", 389.35, "joao"));





    }

    private View.OnClickListener cadastrarProduto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            arrayProduto.add(new Produto(eTModelo.getText().toString(), eTMarca.getText().toString(),Double.parseDouble(eTValor.getText().toString()),spFornecedor.getSelectedItem().toString()));
            intent = new Intent (getApplicationContext(), Menu.class);
            intent.putParcelableArrayListExtra("PRODUTOS", arrayProduto);
            startActivity(intent);

        }
    };
}
