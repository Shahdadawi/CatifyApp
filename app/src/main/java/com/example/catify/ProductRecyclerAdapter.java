package com.example.catify;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

    private ArrayList<Product> products;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductRecyclerAdapter(ArrayList<Product> products, OnItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        CardView cardView = holder.cardView;

        ImageView imgProduct = cardView.findViewById(R.id.imgProduct);
        TextView txtName = cardView.findViewById(R.id.txtName);
        TextView txtPrice = cardView.findViewById(R.id.txtPrice);
        TextView txtQuantity = cardView.findViewById(R.id.txtQuantity);

        int imageRes = cardView.getContext().getResources()
                .getIdentifier(product.getImageName(), "drawable", cardView.getContext().getPackageName());

        if (imageRes != 0) {
            Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageRes);
            imgProduct.setImageDrawable(drawable);
        } else {
            imgProduct.setImageResource(R.drawable.bed_frisco);
        }

        txtName.setText(product.getName());
        txtPrice.setText("$" + product.getPrice());
        txtQuantity.setText(product.getQuantity() + " units");

        cardView.setOnClickListener(v -> listener.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(ArrayList<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
