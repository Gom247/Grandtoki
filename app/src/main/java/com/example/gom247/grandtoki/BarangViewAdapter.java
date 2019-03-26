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

public class BarangViewAdapter extends RecyclerView.Adapter<BarangViewAdapter.ViewHolder> {

    private Context context;
    private List<BarangAdapter> respone_barang;

    public BarangViewAdapter(Context context, List<BarangAdapter> respone_barang) {

        this.context = context;
        this.respone_barang = respone_barang;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BarangAdapter barang = respone_barang.get(position);
        holder.txtKodeBarang.setText(barang.getKode_barang());
        holder.txtNamaBarang.setText(barang.getNama_barang());
        holder.txtJenisBarang.setText(barang.getJenis_barang());
        holder.txtProdukBarang.setText(barang.getProduk_barang());
        holder.txtHargaBarang.setText(barang.getHarga_barang());

    }

    @Override
    public int getItemCount() {
        return respone_barang.size();
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
