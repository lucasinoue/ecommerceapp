package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    protected Button btCancelarCadastroCliente;
    protected Button btLogin;
    protected SQLiteDatabase db;
    protected ListView clientes;

    ArrayAdapter<Cliente> arrayAdapter;
    ArrayList<Cliente> arrayList;
    Banco  banco;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_cliente);

        edNomeCliente = (EditText) findViewById(R.id.edtNomeCliente);
        edEmailCliente = (EditText) findViewById(R.id.edtEmailCliente);
        edSenhaCliente = (EditText) findViewById(R.id.edtSenhaCliente);

        btCadastrarCliente = (Button) findViewById(R.id.btnCadCliente);
        btCancelarCadastroCliente = (Button) findViewById(R.id.btnCancelCadCliente);
        btLogin = (Button) findViewById(R.id.btnLogin);
        banco = new Banco(this);
        db = banco.getWritableDatabase();
       // clientes = (ListView) findViewById(R.id.lvClientes);

      //  Cliente cliente = new Cliente("jose","jose@jose.com", "jose");

      //  arrayList.add(cliente);

      /*  arrayAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, arrayList);
        clientes.setAdapter(arrayAdapter);

*/
        btCadastrarCliente.setOnClickListener(cadastrarCliente);
        btCancelarCadastroCliente.setOnClickListener(cancelarCliente);

    }

    View.OnClickListener cadastrarCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ContentValues values = new ContentValues();
            values.put(Banco.TBCliente.NOME, edNomeCliente.getText().toString());
            values.put(Banco.TBCliente.EMAIL, edEmailCliente.getText().toString());
            values.put(Banco.TBCliente.SENHA, edSenhaCliente.getText().toString());

            long idCliente = db.insert(Banco.TBCliente.TABLE,null,values);
            if(!String.valueOf(idCliente).isEmpty()){
                Toast.makeText(getApplicationContext(), "cadCliente deu boa", Toast.LENGTH_LONG).show();
                Notification.Builder noBuilder = new Notification.Builder(getApplicationContext())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Banco")
                        .setContentText("deuBoa");
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1,noBuilder.build());
                finish();

            }else{
                Toast.makeText(getApplicationContext(), "cadCliente algo deu errado", Toast.LENGTH_LONG).show();
            }
          /*  Cliente cliente = new Cliente(edNomeCliente.getText().toString(),edEmailCliente.getText().toString(),edSenhaCliente.getText().toString());
            //arrayList.add(cliente);
            arrayAdapter.add(cliente);
            arrayAdapter.setNotifyOnChange(true);*/
        }
    };
    View.OnClickListener apagar = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String whereClause = "email = ?";
            String[] whereArgs = {edEmailCliente.getText().toString()};
                db.delete(Banco.TBCliente.TABLE, whereClause,whereArgs);

        }
        };

    View.OnClickListener cancelarCliente = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener atualizar = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Banco.TBCliente.NOME,edNomeCliente.getText().toString());
            contentValues.put(Banco.TBCliente.EMAIL,edEmailCliente.getText().toString());
            contentValues.put(Banco.TBCliente.SENHA,edSenhaCliente.getText().toString());

            String whereClause = "email = ?";
            String[] whereArgs = {edEmailCliente.getText().toString()};

            db.update(Banco.TBCliente.TABLE, contentValues, whereClause,whereArgs);

        }
    };
        View.OnClickListener carregarDadosCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] columns = {Banco.TBCliente.NOME,Banco.TBCliente.EMAIL };
            String selection = null;
            String[] selectionArgs = null;
            String groupBy = null;
            String having = null;
            String orderBy = Banco.TBCliente._ID + " ASC";
            Cursor cursor =  db.query(Banco.TBCliente.TABLE,columns,selection,selectionArgs,groupBy,having,orderBy);

            try {
                while(cursor.moveToNext()){
                    cursor.getString(cursor.getColumnIndex(Banco.TBCliente.NOME));
                    cursor.getString(cursor.getColumnIndex(Banco.TBCliente.EMAIL));
                }
            }finally {
                db.close();
            }

//            while(cursor.moveToNext()){
//                cursor.getString(cursor.getColumnIndex(Banco.TBCliente.NOME));
//                cursor.getString(cursor.getColumnIndex(Banco.TBCliente.EMAIL));
//            }
//            db.close();
        }
    };



    public Cursor carregaDadoByEmail(String Email){
    Cursor cursor;
        Toast.makeText(this, "teste bacno login", Toast.LENGTH_LONG).show();
    String[] campos =  {Banco.TBCliente.EMAIL,Banco.TBCliente.SENHA,Banco.TBCliente.NOME};
    String where = Banco.TBCliente.EMAIL + "=" + Email;
    db = banco.getReadableDatabase();
    cursor = db.query(Banco.TBCliente.TABLE,campos,where, null, null, null, null, null);



    if(cursor!=null){
        cursor.moveToFirst();
    }
    db.close();
    return cursor;
}
}


