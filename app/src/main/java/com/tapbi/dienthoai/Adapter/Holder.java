package com.tapbi.dienthoai.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tapbi.dienthoai.R;

public class Holder extends RecyclerView.ViewHolder {
    public TextView tvRowTen;
    public TextView tvRowSdt;
    public Holder(@NonNull View itemView) {
        super(itemView);
        tvRowTen = itemView.findViewById(R.id.tvRcvTen);
        tvRowSdt = itemView.findViewById(R.id.tvRcvSdt);
    }
}
