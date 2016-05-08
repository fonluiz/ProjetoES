package com.example.projetoes.projetoes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetoes.projetoes.Interfaces.RecycleViewOnClickListener;
import com.example.projetoes.projetoes.Models.Card;
import com.example.projetoes.projetoes.R;

import java.util.List;

/**
 * Created by samirsmedeiros on 05/04/16.
 */
public class LostFeedCardAdapter extends RecyclerView.Adapter<LostFeedCardAdapter.MyViewHolder> {

    private List<Card> mList;
    private LayoutInflater mLayoutInflater;
    private RecycleViewOnClickListener mRecycleViewOnClickListener;



    public LostFeedCardAdapter(Context c, List<Card> list){
        mList = list;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.item_obj_card, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        holder.ivCard.setImageURI(mList.get(position).getFoto());
        holder.tvTitulo.setText(mList.get(position).getTitulo());
        holder.tvBairro.setText(mList.get(position).getBairro());
        holder.tvRecompensa.setText(mList.get(position).getRecompensa());

    }

    public void setRecycleViewOnClickListener(RecycleViewOnClickListener r){
        mRecycleViewOnClickListener = r;
    }


    public void addListItem(Card c, int position){
        mList.add(c);
//        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
 //       notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView ivCard;
        public TextView tvTitulo;
        public TextView tvBairro;
        public TextView tvRecompensa;

        public MyViewHolder(View itenView){
            super(itenView);

            ivCard = (ImageView) itenView.findViewById(R.id.iv_card);
            tvTitulo = (TextView) itenView.findViewById(R.id.tv_titulo);
            tvBairro = (TextView) itenView.findViewById(R.id.tv_bairro);
            tvRecompensa = (TextView) itenView.findViewById(R.id.tv_recompensa);


            itenView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mRecycleViewOnClickListener != null){
                mRecycleViewOnClickListener.onClickListener(v,getAdapterPosition());
            }
        }
    }


}
