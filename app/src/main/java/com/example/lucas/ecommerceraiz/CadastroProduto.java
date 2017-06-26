package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    SQLiteDatabase db;
    Banco  banco;
    long idProduto;
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


        arrayProduto =  getIntent().getParcelableArrayListExtra("FORNECEDORES");



    }

    private View.OnClickListener cadastrarProduto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            ContentValues values = new ContentValues();
            values.put(Banco.TBProduto.MARCA, eTMarca.getText().toString());
            values.put(Banco.TBProduto.MODELO, eTModelo.getText().toString());
            values.put(Banco.TBProduto.VALOR, eTValor.getText().toString());
            values.put(Banco.TBProduto.FORNECEDOR, spFornecedor.getId());

            idProduto = db.insert(Banco.TBFornecedor.TABLE,null,values);
            if(!String.valueOf(idProduto).isEmpty()){
                Toast.makeText(getApplicationContext(), "cadProduto deu boa", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(), "cadProduto algo deu errado", Toast.LENGTH_LONG).show();
            }


            /*arrayProduto.add(new Produto(eTModelo.getText().toString(), eTMarca.getText().toString(),Double.parseDouble(eTValor.getText().toString()),spFornecedor.getSelectedItem().toString()));
            intent = new Intent (getApplicationContext(), Menu.class);
            intent.putParcelableArrayListExtra("PRODUTOS", arrayProduto);
            startActivity(intent);*/

        }
    };

    View.OnClickListener apagar = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String whereClause = "MODELO = ?";
            String[] whereArgs = {eTModelo.getText().toString()};
            db.delete(Banco.TBCliente.TABLE, whereClause,whereArgs);

        }
    };

    View.OnClickListener atualizar = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Banco.TBProduto.MODELO,eTModelo.getText().toString());
            contentValues.put(Banco.TBProduto.MARCA,eTMarca.getText().toString());
            contentValues.put(Banco.TBProduto.FORNECEDOR,spFornecedor.getSelectedItem().toString());

            String whereClause = "email = ?";
            String[] whereArgs = {eTModelo.toString()};

            db.update(Banco.TBProduto.TABLE, contentValues, whereClause,whereArgs);

        }
    };
    View.OnClickListener CarregarTodosProdutos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] columns = {Banco.TBProduto.ID_FORNECEDOR,Banco.TBProduto.FORNECEDOR, Banco.TBProduto.MARCA,Banco.TBProduto.MODELO,Banco.TBProduto.VALOR};
            String selection = null;
            String[] selectionArgs = null;
            String groupBy = null;
            String having = null;
            String orderBy = Banco.TBProduto._ID + " ASC";
            Cursor cursor =  db.query(Banco.TBProduto.TABLE,columns,selection,selectionArgs,groupBy,having,orderBy);
            while(cursor.moveToNext()){
                cursor.getString(cursor.getColumnIndex(Banco.TBProduto.MODELO));
                cursor.getString(cursor.getColumnIndex(Banco.TBProduto.MARCA));
                cursor.getString(cursor.getColumnIndex(Banco.TBProduto.FORNECEDOR));
            }

        }
    };



    public Cursor carregarByModelo(String modelo){
        Cursor cursor;

        String[] campos =  {Banco.TBProduto.MODELO,Banco.TBProduto.MARCA,Banco.TBProduto.FORNECEDOR};
        String where = Banco.TBProduto.MODELO + "=" + modelo;
        db = banco.getReadableDatabase();
        cursor = db.query(Banco.TBProduto.TABLE,campos,where, null, null, null, null, null);


        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
