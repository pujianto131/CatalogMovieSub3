package com.pujianto131.catalogmovieuiuxpakeko.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pujianto131.catalogmovieuiuxpakeko.R;
import com.pujianto131.catalogmovieuiuxpakeko.model.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
    private List<ResultItem> list= new ArrayList<>();


    public MovieAdapter(){
        //nothing
    }
    public void clearAll(){
        list.clear();
        notifyDataSetChanged();
    }
    public void replaceAll(List<ResultItem>items){
        list.clear();
        list=items;
        notifyDataSetChanged();
    }
    public void updatedData(List<ResultItem>items){
        list.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_detail_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
