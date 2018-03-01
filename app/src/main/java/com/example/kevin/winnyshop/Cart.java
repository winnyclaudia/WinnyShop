package com.example.kevin.winnyshop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.winnyshop.Adapter.RecyclerAdapter;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    Button _button;
    TextView _total;
    RecyclerAdapter adapter;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        openHelper = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        _button = (Button) findViewById(R.id.btnBayar);
        _total = (TextView) findViewById(R.id.txtTotal);

        db=openHelper.getWritableDatabase();
        cursor = db.rawQuery("Select SUM(Harga) from "+DatabaseHelper.Table_Cart, null);
        cursor.moveToNext();
        if (cursor.getString(0) == null) {
            _total.setText("Total = 0");
        } else {
            _total.setText("Total = "+cursor.getString(0));
        }
        db.close();

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                db.delete(DatabaseHelper.Table_Cart,null,null);
                db.close();
                Toast.makeText(getApplicationContext(), "Pembayaran Sukses", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        adapter = new RecyclerAdapter(Cart.this,databaseHelper.findAll());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
