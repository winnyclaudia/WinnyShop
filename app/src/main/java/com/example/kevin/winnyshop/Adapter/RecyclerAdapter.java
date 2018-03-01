package com.example.kevin.winnyshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevin.winnyshop.Cart;
import com.example.kevin.winnyshop.R;
import com.example.kevin.winnyshop.Temp;

import java.util.List;

/**
 * Created by kevin on 23/02/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    List<Temp> temps;
    Context context;

    public RecyclerAdapter(Context context, List<Temp> temps) {
        this.context = context;
        this.temps = temps;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new RecyclerViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (temps != null) {
            Temp temp = temps.get(position);
            holder._id.setText(temp.getId());
            holder._nama.setText(temp.getNama());
            holder._console.setText(temp.getConsole());
            holder._harga.setText(temp.getHarga());
        }
    }

    @Override
    public int getItemCount() {
        return temps.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView _id, _nama, _console, _harga;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            _id = (TextView) itemView.findViewById(R.id.txtID);
            _nama = (TextView) itemView.findViewById(R.id.txtNama);
            _console = (TextView) itemView.findViewById(R.id.txtConsole);
            _harga = (TextView) itemView.findViewById(R.id.txtHarga);
        }
    }
}
