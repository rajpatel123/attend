package com.example.kishan.recyclerrrr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kishan.recyclerrrr.Models.addagentbyid.GetDealerByIdResponse;
import com.example.kishan.recyclerrrr.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<GetDealerByIdResponse> dealrList;
    Context context;

    public MoviesAdapter(Context context, List<GetDealerByIdResponse> body) {
        this.context=context;
        this.dealrList = body;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public void setdata(List<GetDealerByIdResponse> moviesList) {
        this.dealrList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetDealerByIdResponse movie = dealrList.get(position);
        holder.title.setText(movie.getFirstName());
        holder.genre.setText(movie.getLastName());
        holder.year.setText(movie.getPhone());
    }

    @Override
    public int getItemCount() {
        return dealrList.size();
    }
}