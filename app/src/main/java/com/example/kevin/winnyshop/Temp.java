package com.example.kevin.winnyshop;

import java.io.Serializable;




public class Temp implements Serializable {

    private String id;
    private String nama;
    private String console;
    private String harga;

    public Temp(String id, String nama, String console, String harga) {
        this.id = id;
        this.nama = nama;
        this.console = console;
        this.harga = harga;
    }

    public Temp(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
