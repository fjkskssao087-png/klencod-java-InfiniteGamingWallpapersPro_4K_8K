package com.infinite.wallpapers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {

    private Context context;
    private List<WallpaperItem> list;

    public WallpaperAdapter(Context context, List<WallpaperItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wallpaper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WallpaperItem item = list.get(position);
        holder.titleText.setText(item.getTitle());
        holder.categoryText.setText(item.getCategory());
        holder.resBadge.setText(item.getResolution());

        ImageLoader.load(item.getImageUrl(), holder.wallpaperImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", item.getTitle());
            intent.putExtra("category", item.getCategory());
            intent.putExtra("image", item.getImageUrl());
            intent.putExtra("res", item.getResolution());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, categoryText, resBadge;
        ImageView wallpaperImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.itemTitle);
            categoryText = itemView.findViewById(R.id.itemCategory);
            resBadge = itemView.findViewById(R.id.itemRes);
            wallpaperImage = itemView.findViewById(R.id.itemImage);
        }
    }
}
