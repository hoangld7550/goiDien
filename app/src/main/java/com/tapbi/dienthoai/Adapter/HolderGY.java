package com.tapbi.dienthoai.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tapbi.dienthoai.R;

public class HolderGY extends RecyclerView.ViewHolder {
    public TextView tvRowTen;
    public TextView tvRowSdt;
    public HolderGY(@NonNull View itemView) {
        super(itemView);
        tvRowTen = itemView.findViewById(R.id.tvRcvTenGY);
        tvRowSdt = itemView.findViewById(R.id.tvRcvSdtGY);
    }
}
