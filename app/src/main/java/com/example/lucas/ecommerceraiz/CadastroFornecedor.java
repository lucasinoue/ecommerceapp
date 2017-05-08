package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Fornecedor;

import java.util.ArrayList;

/**
 * Created by Sidimar on 08/05/2017.
 */

public class CadastroFornecedor extends Activity {

    ArrayList<Fornecedor> arrayFornecedor;
    Fornecedor fornecedores;
    Intent intent;

    Button confirmar;
    EditText eTRasaoSocial;
    EditText eTResponsavel;
    EditText eTCNPJ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_fornecedor);
        arrayFornecedor = new ArrayList<Fornecedor>();

        confirmar = (Button) findViewById(R.id.btnCad_fornecedor_confirmar);
        eTRasaoSocial = (EditText) findViewById(R.id.edtCadRasaoSocial);
        eTResponsavel = (EditText) findViewById(R.id.edtCadResponsavel);
        eTCNPJ = (EditText) findViewById(R.id.edtCadCNPJ);





    }

    private View.OnClickListener cadastrarForncedor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            arrayFornecedor.add(new Fornecedor(eTRasaoSocial.getText().toString(),eTResponsavel.getText().toString(),Double.parseDouble(eTCNPJ.getText().toString())));
            intent= new Intent(getApplicationContext(), CadastroProduto.class);
            intent.putParcelableArrayListExtra("FORNECEDORES", arrayFornecedor);
            startActivity(intent);
        }
    };
}
