package com.example.catify;

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

public class WishlistRecyclerAdapter extends RecyclerView.Adapter<WishlistRecyclerAdapter.ViewHolder> {

    private ArrayList<Product> wishlist;
    private WishlistActivity activity;

    public WishlistRecyclerAdapter(WishlistActivity activity, ArrayList<Product> wishlist) {
        this.activity = activity;
        this.wishlist = wishlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = wishlist.get(position);
        CardView cardView = holder.cardView;

        ImageView imgProduct = cardView.findViewById(R.id.imgProduct);
        TextView txtName = cardView.findViewById(R.id.txtName);
        TextView txtPrice = cardView.findViewById(R.id.txtPrice);
        Button btnRemove = cardView.findViewById(R.id.btnRemove);

        int imageRes = cardView.getContext().getResources()
                .getIdentifier(product.getImageName(), "drawable", cardView.getContext().getPackageName());

        if (imageRes != 0) {
            Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageRes);
            imgProduct.setImageDrawable(drawable);
        } else {

            imgProduct.setImageResource(R.drawable.bed_meowfia);
        }

        txtName.setText(product.getName());
        txtPrice.setText("$" + product.getPrice());

        cardView.setOnClickListener(v -> {
            activity.startActivity(new android.content.Intent(activity, ProductActivity.class)
                    .putExtra("product", product));
        });


        btnRemove.setOnClickListener(v -> activity.removeFromWishlist(product));
    }


    @Override
    public int getItemCount() {
        return wishlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
