package com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sidimar on 05/05/2017.
 */

public class Cliente implements Parcelable{

    private String nome;
    private String email;
    private String senha;



    public Cliente (String nome, String email, String senha){
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//transforma o objeto em uma string
            dest.writeString(nome);
            dest.writeString(email);
            dest.writeString(senha);
    }

    public Cliente (Parcel in){//transforma a string novamnete em objeto
        this.setNome(in.readString());
        this.setEmail(in.readString());
        this.setSenha(in.readString());
    }
    public static final Creator<Cliente> CREATOR  = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel source) {
            return new Cliente(source);//transforma a serialização novamente em objeto
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
