package com.example.gom247.grandtoki;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gom247.grandtoki.adapter.BarangAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gom247 on 26/03/2019.
 */

public class BarangViewAdapter2 extends RecyclerView.Adapter<BarangViewAdapter2.ViewHolder> {

    private Context context;
    private List<BarangAdapter> data_produk;

    public BarangViewAdapter2(Context context, List<BarangAdapter> data_produk) {
        this.context = context;
        this.data_produk = data_produk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BarangAdapter barang = data_produk.get(position);
        holder.txtKodeBarang.setText(barang.getKode_barang());
        holder.txtNamaBarang.setText(barang.getNama_barang());
        holder.txtJenisBarang.setText(barang.getJenis_barang());
        holder.txtProdukBarang.setText(barang.getProduk_barang());
        holder.txtHargaBarang.setText(barang.getHarga_barang());

    }

    @Override
    public int getItemCount() {
        return data_produk.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtKodeBarang)
        TextView txtKodeBarang;
        @BindView(R.id.txtNamaBarang)
        TextView txtNamaBarang;
        @BindView(R.id.txtJenisBarang)
        TextView txtJenisBarang;
        @BindView(R.id.txtProdukBarang)
        TextView txtProdukBarang;
        @BindView(R.id.txtHargaBarang)
        TextView txtHargaBarang;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
