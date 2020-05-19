package com.tapbi.dienthoai.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tapbi.dienthoai.Model.ContactModel;

import com.tapbi.dienthoai.R;
import com.tapbi.dienthoai.iClick;

import java.util.List;

public class AdapterGY extends RecyclerView.Adapter<HolderGY> {
    Context context;
    private List<ContactModel> list;
    private iClick iClick;

    public AdapterGY(Context context, List<ContactModel> list, iClick iClick) {
        this.context = context;
        this.list = list;
        this.iClick = iClick;
    }

    @NonNull
    @Override
    public HolderGY onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_gy, parent, false);
        HolderGY holder= new HolderGY(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderGY holder, int position) {
        final ContactModel contactModel = list.get(position);
        holder.tvRowTen.setText(contactModel.name);
        holder.tvRowSdt.setText(contactModel.mobileNumber);

        holder.tvRowSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClick.nhan(contactModel.mobileNumber);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}
