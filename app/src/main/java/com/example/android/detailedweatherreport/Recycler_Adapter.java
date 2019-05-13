package com.example.android.detailedweatherreport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.TestViewHolder>{

    ArrayList<List_item> list_items;
    Context context;
    String[] keys;

    Recycler_Adapter(ArrayList<List_item> list_items1, Context context1){
        list_items = list_items1;
        context = context1;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder vh, int i) {

        List_item object = list_items.get(i);

        String firstText = object.getCategory();
        String secondText = object.getValue();

        vh.category.setText(firstText);
        vh.value.setText(secondText);
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class TestViewHolder extends RecyclerView.ViewHolder{
        TextView category;
        TextView value;
        TestViewHolder(@NonNull View view){
            super(view);
            category = view.findViewById(R.id.category);
            value = view.findViewById(R.id.value);
        }
    }
}
