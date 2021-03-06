package com.tapbi.dienthoai.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tapbi.dienthoai.MainActivity;
import com.tapbi.dienthoai.Model.ContactModel;
import com.tapbi.dienthoai.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {
    Context context;
    private List<ContactModel> list;

    public Adapter(Context context, List<ContactModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        Holder holder= new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ContactModel contactModel = list.get(position);
        holder.tvRowTen.setText(contactModel.name);
        holder.tvRowSdt.setText(contactModel.mobileNumber);

        holder.tvRowSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, MainActivity.class);
                intent.putExtra("sdt", contactModel.mobileNumber);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
