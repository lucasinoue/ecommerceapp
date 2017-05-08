package com.example.lucas.ecommerceraiz.com.example.lucas.ecommerceraiz.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sidimar on 08/05/2017.
 */

public class Fornecedor implements Parcelable {
        private String rasaoSocial;
        private String responsavel;
        private double CNPJ;

    public Fornecedor(String rasaoSocial, String responsavel, double CNPJ){
        this.setRasaoSocial(rasaoSocial);
        this.setResponsavel(responsavel);
        this.setCNPJ(CNPJ);
    }

    public Fornecedor (Parcel in){
        setRasaoSocial(in.readString());
        setResponsavel(in.readString());
        setCNPJ(in.readDouble());
    }

    public static final Creator<Fornecedor> CREATOR = new Creator<Fornecedor>() {
        @Override
        public Fornecedor createFromParcel(Parcel source) {
            return new Fornecedor(source);
        }

        @Override
        public Fornecedor[] newArray(int size) {
            return new Fornecedor[size];
        }
    };
    @Override
    public int describeContents() {
        return 2;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getRasaoSocial());
        dest.writeString(getResponsavel());
        dest.writeDouble(getCNPJ());
    }

    public String getRasaoSocial() {
        return rasaoSocial;
    }

    public void setRasaoSocial(String rasaoSocial) {
        this.rasaoSocial = rasaoSocial;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public double getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(double CNPJ) {
        this.CNPJ = CNPJ;
    }
}
