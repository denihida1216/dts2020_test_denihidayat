package com.denihidayat.teslsp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denihidayat.teslsp.sqllite.BarangDataitem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {
    List<BarangDataitem> barangList;

    public BarangAdapter(List<BarangDataitem> barangList) {
        this.barangList = barangList;
    }

    @NonNull
    @Override
    public BarangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.barang_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.MyViewHolder holder, int position) {
        BarangDataitem list = barangList.get(position);
        holder.nama.setText("Nama : "+list.getNama());
        holder.harga.setText("Harga : Rp."+toMoney(list.getHarga()));
        holder.merk.setText("Merk : "+list.getMerk());
    }

    @Override
    public int getItemCount() {
        if (barangList == null) {
            return 0;
        }
        return barangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        public TextView harga;
        public TextView merk;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            merk = itemView.findViewById(R.id.merk);
        }
    }

    public String toMoney(Double val) {
        return String.format("%,d", new Object[]{Long.valueOf(Math.round(val))});
    }
}
