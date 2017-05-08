package com.example.lucas.ecommerceraiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities.Produto;

import java.util.ArrayList;

/**
 * Created by Sidimar on 06/05/2017.
 */

public class Menu extends Activity {
    ListView listViewProdutos;
    Intent intent;
    ArrayAdapter <Produto> arrayAdapter;

    Parcelable[] parcelables;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        listViewProdutos = (ListView)findViewById(R.id.LVProdutos);
        intent = getIntent();

        ArrayList<Produto>  produto =  getIntent().getParcelableArrayListExtra("PRODUTOS");

        arrayAdapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produto);

        listViewProdutos.setAdapter(arrayAdapter);
        arrayAdapter.setNotifyOnChange(true);
}
}
