package com.example.catify;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> products;
    private CartActivity activity;

    public CartRecyclerAdapter(Context context, ArrayList<Product> products, CartActivity activity) {
        this.context = context;
        this.products = products;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v  = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        CardView cardView = holder.cardView;

        ImageView imgProduct = cardView.findViewById(R.id.imgProduct);
        TextView txtName = cardView.findViewById(R.id.txtName);
        TextView txtPrice = cardView.findViewById(R.id.txtPrice);
        TextView txtQuantity = cardView.findViewById(R.id.txtQuantity);
        Button btnRemove = cardView.findViewById(R.id.btnRemove);

        Drawable drawable = ContextCompat.getDrawable(context,
                context.getResources().getIdentifier(product.getImageName(), "drawable", context.getPackageName()));
        imgProduct.setImageDrawable(drawable);

        txtName.setText(product.getName());
        txtPrice.setText("$" + product.getPrice());
        txtQuantity.setText(product.getQuantity() + " units");

        btnRemove.setOnClickListener(v -> activity.removeFromCart(product));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
