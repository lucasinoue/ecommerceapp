package com.example.lucas.ecommerceraiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Sidimar on 23/06/2017.
 */

public class Banco extends SQLiteOpenHelper {

    public static final String BANCO = "db_ecommerce";
    public static final int VERSAO = 1;

    public static abstract class TBCliente implements BaseColumns{
        public static final String TABLE = "cliente";
        public static final String NOME = "nome";
        public static final String EMAIL = "email";
        public static final String SENHA = "senha";

    }
    public static abstract class TBFornecedor implements BaseColumns{
        public static final String TABLE = "fornecedor";
        public static final String RASAOSOCIAL = "rasaosocial";
        public static final String RESPONSAVEL = "responsavel";
        public static final String CNPJ = "cnpj";

    }
    public static abstract class TBProduto implements BaseColumns{
        public static final String TABLE = "produto";
        public static final String MARCA = "marca";
        public static final String MODELO = "modelo";
        public static final String VALOR = "valor";
        public static final String ID_FORNECEDOR = "id_fornecedor";
        public static final String FORNECEDOR = "fornecedor";

    }



    public Banco(Context context) {
        super(context, BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        //cliente
        String sql = builder.append(" CREATE TABLE " + TBCliente.TABLE)
                .append("(")
                .append(TBCliente._ID + " INTEGER PRIMARY KEY,")
                .append(TBCliente.NOME + " TEXT NOT NULL,")
                .append(TBCliente.EMAIL + " TEXT NOT NULL,")
                .append(TBCliente.SENHA + " TEXT NOT NULL")
                .append(");")

        //fornecedor
            .append(" CREATE TABLE " + TBFornecedor.TABLE)
                .append("(")
                .append(TBFornecedor._ID + " INTEGER PRIMARY KEY,")
                .append(TBFornecedor.RASAOSOCIAL + " TEXT NOT NULL,")
                .append(TBFornecedor.CNPJ + " TEXT NOT NULL,")
                .append(TBFornecedor.RESPONSAVEL + " TEXT NOT NULL")
                .append("); ")

        //produto
            .append(" CREATE TABLE " + TBProduto.TABLE)
                .append("(")
                .append(TBProduto._ID + " INTEGER PRIMARY KEY, ")
                .append(TBProduto.MARCA + " TEXT NOT NULL,")
                .append(TBProduto.MODELO + " TEXT NOT NULL,")
                .append(TBProduto.VALOR + " TEXT NOT NULL,")
                .append("FOREIGN KEY ("+TBProduto.ID_FORNECEDOR+") REFERENCES "+TBFornecedor.TABLE+"("+TBFornecedor._ID+")")
                .append(");").toString();
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
