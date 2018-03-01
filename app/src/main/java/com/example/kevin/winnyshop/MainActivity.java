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

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnRegister,_btnLogin,_btnInsert;
    EditText _edtUser, _edtPass;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);

        _btnRegister = (Button) findViewById(R.id.btnRegister);
        _edtUser = (EditText) findViewById(R.id.edtUser);
        _edtPass = (EditText) findViewById(R.id.edtPass);
        _btnLogin = (Button) findViewById(R.id.btnLogin);

        _btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                String fuser = _edtUser.getText().toString();
                String fpass = _edtPass.getText().toString();
                if ((!"".equals(fuser)) || (!"".equals(fpass))) {
                    insertdataUsr(fuser,fpass);
                    Toast.makeText(getApplicationContext(), "Register Succsessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data Tidak Lengkap", Toast.LENGTH_SHORT).show();
                }
            }

        });

        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                String user = _edtUser.getText().toString();
                String pass = _edtPass.getText().toString();
                cursor = db.rawQuery("Select * From " + DatabaseHelper.Table_User + " Where " + DatabaseHelper.UsrCol_1 + " =? AND " + DatabaseHelper.UsrCol_2 + "=?", new String[]{user,pass});
                if (cursor != null) {
                    if (cursor.getCount()>0) {
                        cursor.moveToNext();
                        db.delete(DatabaseHelper.Table_Cart,null,null);
                        db.close();
                        _edtPass.setText("");
                        _edtUser.setText("");
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Username Atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void insertdataUsr (String fuser, String fpass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.UsrCol_1, fuser);
        contentValues.put(DatabaseHelper.UsrCol_2, fpass);
        Long id = db.insert(DatabaseHelper.Table_User, null, contentValues);
    }
}
