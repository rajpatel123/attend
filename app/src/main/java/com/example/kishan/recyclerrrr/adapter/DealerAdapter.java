package com.example.kishan.recyclerrrr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kishan.recyclerrrr.modelClass.addagentbyid.GetDealerByIdResponse;
import com.example.kishan.recyclerrrr.R;

import java.util.List;

public class DealerAdapter extends RecyclerView.Adapter<DealerAdapter.MyViewHolder> {

    private List<GetDealerByIdResponse> dealerList;
    Context context;
    public DealerAdapter(Context context) {
        this.context = context;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName, email, lastName;

        public MyViewHolder(View view) {
            super(view);
            email = (TextView) view.findViewById(R.id.ET_EMAIL);
            firstName = (TextView) view.findViewById(R.id.Et_NAME);
            lastName = (TextView) view.findViewById(R.id.ET_LTNAME);
        }
    }


    public void setdata(List<GetDealerByIdResponse> moviesList) {
        this.dealerList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetDealerByIdResponse getDealerByIdResponse = dealerList.get(position);
        holder.email.setText(getDealerByIdResponse.getEmail());
        holder.firstName.setText(getDealerByIdResponse.getFirstName());
        holder.lastName.setText(getDealerByIdResponse.getLastName());
    }

    @Override
    public int getItemCount() {
        return dealerList.size();
    }
}