package com.example.gom247.grandtoki.adapter;

import java.util.List;

/**
 * Created by Gom247 on 14/03/2019.
 */

public class ResponAdapter {

    private String message;
    private String error;
    private List<BarangAdapter> respone_barang;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<BarangAdapter> getRespone_barang() {
        return respone_barang;
    }

    public void setRespone_barang(List<BarangAdapter> respone_barang) {
        this.respone_barang = respone_barang;
    }
}
