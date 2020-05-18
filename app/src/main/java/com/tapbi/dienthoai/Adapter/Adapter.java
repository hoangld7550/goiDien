package com.tapbi.dienthoai.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tapbi.dienthoai.Model.ContactModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {
    Context context;
    private List<ContactModel> list;


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
