package com.example.kevin.winnyshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnATC, _btnSearch, _btnTC, _btnInsert, _btnClear, _btnDelete;
    EditText _edtID, _edtNama, _edtConsole, _edtHarga;
    Cursor cursor;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        openHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        if (receivedIntent.hasExtra("user")){
            user = receivedIntent.getStringExtra("user");
        }

        _btnATC = (Button) findViewById(R.id.btnATC);
        _btnTC = (Button) findViewById(R.id.btnTC);
        _btnInsert = (Button) findViewById(R.id.btnInsert);
        _btnClear = (Button) findViewById(R.id.btnClear);
        _btnSearch = (Button) findViewById(R.id.btnSearch);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _edtID = (EditText) findViewById(R.id.edtID);
        _edtNama = (EditText) findViewById(R.id.edtNama);
        _edtConsole = (EditText) findViewById(R.id.edtConsole);
        _edtHarga = (EditText) findViewById(R.id.edtHarga);

        if ("admin".equals(user)){
            _btnInsert.setEnabled(true);
            _btnInsert.setVisibility(View.VISIBLE);

            _btnClear.setEnabled(true);
            _btnClear.setVisibility(View.VISIBLE);

            _btnDelete.setEnabled(true);
            _btnDelete.setVisibility(View.VISIBLE);

            _edtNama.setFocusable(true);
            _edtNama.setFocusableInTouchMode(true);
            _edtNama.setClickable(true);
            _edtNama.setCursorVisible(true);

            _edtConsole.setFocusable(true);
            _edtConsole.setFocusableInTouchMode(true);
            _edtConsole.setClickable(true);
            _edtConsole.setCursorVisible(true);

            _edtHarga.setFocusable(true);
            _edtHarga.setFocusableInTouchMode(true);
            _edtHarga.setClickable(true);
            _edtHarga.setCursorVisible(true);
        }

        _btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                String ID = _edtID.getText().toString();
                cursor = db.rawQuery("Select * From " + DatabaseHelper.Table_Barang + " Where " + DatabaseHelper.BrgCol_1 + " =?", new String[]{ID});
                if (cursor != null) {
                    if (cursor.getCount()>0) {
                        cursor.moveToNext();
                        _edtNama.setText(cursor.getString(1));
                        _edtConsole.setText(cursor.getString(2));
                        _edtHarga.setText(cursor.getString(3));
                    } else {
                        Toast.makeText(getApplicationContext(), "Barang Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
                db.close();
            }
        });

        _btnATC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                String ID = _edtID.getText().toString();
                String nama = _edtNama.getText().toString();
                String console = _edtConsole.getText().toString();
                String harga = _edtHarga.getText().toString();
                if (!"".equals(nama)) {
                    insertdataCrt(ID,nama,console,harga);
                    _edtID.setText("");
                    _edtNama.setText("");
                    _edtConsole.setText("");
                    _edtHarga.setText("");
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data Kosong", Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });

        _btnTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Cart.class);
                startActivity(intent);
            }
        });

        _btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                String ID = _edtID.getText().toString();
                String nama = _edtNama.getText().toString();
                String console = _edtConsole.getText().toString();
                String harga = _edtHarga.getText().toString();
                if (!"".equals(nama)) {
                    insertdataBrg(ID,nama,console,harga);
                    _edtID.setText("");
                    _edtNama.setText("");
                    _edtConsole.setText("");
                    _edtHarga.setText("");
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data Kosong", Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });

        _btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = _edtID.getText().toString();
                String nama = _edtNama.getText().toString();
                String console = _edtConsole.getText().toString();
                String harga = _edtHarga.getText().toString();

                _edtID.setText("");
                _edtNama.setText("");
                _edtConsole.setText("");
                _edtHarga.setText("");
            }
        });

        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();

                String ID = _edtID.getText().toString();
                String nama = _edtNama.getText().toString();
                String console = _edtConsole.getText().toString();
                String harga = _edtHarga.getText().toString();

                _edtID.setText("");
                _edtNama.setText("");
                _edtConsole.setText("");
                _edtHarga.setText("");

                cursor = db.rawQuery("Select * From " + DatabaseHelper.Table_Barang + " Where " + DatabaseHelper.BrgCol_1 + " =?", new String[]{ID});
                if (cursor != null) {
                    if (cursor.getCount()>0) {
                        cursor.moveToNext();
                        db.execSQL("Delete From " +DatabaseHelper.Table_Barang + " Where " +DatabaseHelper.BrgCol_1 + " =?", new String[]{ID});
                        db.close();

                        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "ID Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void insertdataBrg (String ID, String nama, String console, String harga){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.BrgCol_1, ID);
        contentValues.put(DatabaseHelper.BrgCol_2, nama);
        contentValues.put(DatabaseHelper.BrgCol_3, console);
        contentValues.put(DatabaseHelper.BrgCol_4, harga);
        Long id = db.insert(DatabaseHelper.Table_Barang, null, contentValues);
    }

    public void insertdataCrt (String ID, String nama, String console, String harga){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CrtCol_1, ID);
        contentValues.put(DatabaseHelper.CrtCol_2, nama);
        contentValues.put(DatabaseHelper.CrtCol_3, console);
        contentValues.put(DatabaseHelper.CrtCol_4, harga);
        Long id = db.insert(DatabaseHelper.Table_Cart, null, contentValues);
    }
}
