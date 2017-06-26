package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Fornecedor;

import java.util.ArrayList;

/**
 * Created by Sidimar on 08/05/2017.
 */

public class CadastroFornecedor extends Activity {

    ArrayList<Fornecedor> arrayFornecedor;
    Fornecedor fornecedores;
    Intent intent;
    SQLiteDatabase db;
    Button confirmar;
    EditText eTRasaoSocial;
    EditText eTResponsavel;
    EditText eTCNPJ;
    Banco  banco;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_fornecedor);
        arrayFornecedor = new ArrayList<Fornecedor>();

        confirmar = (Button) findViewById(R.id.btnCad_fornecedor_confirmar);
        eTRasaoSocial = (EditText) findViewById(R.id.edtCadRasaoSocial);
        eTResponsavel = (EditText) findViewById(R.id.edtCadResponsavel);
        eTCNPJ = (EditText) findViewById(R.id.edtCadCNPJ);

        Banco banco = new Banco(this);
        db = banco.getWritableDatabase();






    }

    private View.OnClickListener cadastrarForncedor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ContentValues values = new ContentValues();
            values.put(Banco.TBFornecedor.RASAOSOCIAL, eTRasaoSocial.getText().toString());
            values.put(Banco.TBFornecedor.RASAOSOCIAL, eTResponsavel.getText().toString());
            values.put(Banco.TBFornecedor.CNPJ, eTCNPJ.getText().toString());

            long idFornecedor = db.insert(Banco.TBFornecedor.TABLE, null, values);
            if (!String.valueOf(idFornecedor).isEmpty()) {
                Toast.makeText(getApplicationContext(), "cadFornecedor deu boa", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "cadFornecedor algo deu errado", Toast.LENGTH_LONG).show();
            }
            /*arrayFornecedor.add(new Fornecedor(eTRasaoSocial.getText().toString(),eTResponsavel.getText().toString(),(eTCNPJ.getText().toString())));
            intent= new Intent(getApplicationContext(), CadastroProduto.class);
            intent.putParcelableArrayListExtra("FORNECEDORES", arrayFornecedor);
            startActivity(intent);*/
        }};


            View.OnClickListener apagar = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String whereClause = "CNPJ = ?";
                    String[] whereArgs = {eTCNPJ.getText().toString()};
                    db.delete(Banco.TBFornecedor.TABLE, whereClause,whereArgs);

                }
            };

            View.OnClickListener atualizar = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Banco.TBFornecedor.CNPJ,eTCNPJ.getText().toString());
                    contentValues.put(Banco.TBFornecedor.RASAOSOCIAL,eTRasaoSocial.getText().toString());
                    contentValues.put(Banco.TBFornecedor.RESPONSAVEL,eTResponsavel.toString());

                    String whereClause = "cnpj = ?";
                    String[] whereArgs = {eTCNPJ.toString()};

                    db.update(Banco.TBProduto.TABLE, contentValues, whereClause,whereArgs);

                }
            };
            View.OnClickListener CarregarTodosFornecedores = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] columns = {Banco.TBFornecedor.RASAOSOCIAL,Banco.TBFornecedor.RESPONSAVEL,Banco.TBFornecedor.CNPJ};
                    String selection = null;
                    String[] selectionArgs = null;
                    String groupBy = null;
                    String having = null;
                    String orderBy = Banco.TBFornecedor._ID + " ASC";
                    Cursor cursor =  db.query(Banco.TBFornecedor.TABLE,columns,selection,selectionArgs,groupBy,having,orderBy);
                    while(cursor.moveToNext()){
                        cursor.getString(cursor.getColumnIndex(Banco.TBFornecedor.RASAOSOCIAL));
                        cursor.getString(cursor.getColumnIndex(Banco.TBFornecedor.RESPONSAVEL));
                        cursor.getString(cursor.getColumnIndex(Banco.TBFornecedor.CNPJ));
                    }

                }
            };



        public Cursor carregarByCNPJ(String cnpj){
            Cursor cursor;

            String[] campos =  {Banco.TBFornecedor.RASAOSOCIAL,Banco.TBFornecedor.RESPONSAVEL,Banco.TBFornecedor.CNPJ};
            String where = Banco.TBProduto.MODELO + "=" + cnpj;
            db = banco.getReadableDatabase();
            cursor = db.query(Banco.TBFornecedor.TABLE,campos,where, null, null, null, null, null);


            if(cursor!=null){
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

    };

