package com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sidimar on 05/05/2017.
 */

public class Produto implements Parcelable{
        private String marca;
        private String modelo;
        private double valor;
        private String fornecedor;

    @Override
    public int describeContents() {
        return 1;
    }
    public Produto(String marca, String modelo, double valor, String fornecedor){
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setFornecedor(fornecedor);
        this.setValor(valor);
    }

    public Produto(Parcel in){
        marca = in.readString();
        modelo = in.readString();
        valor = in.readDouble();
        fornecedor = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeDouble(valor);
        dest.writeString(fornecedor);
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel source) {
            return new Produto(source);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }


}
