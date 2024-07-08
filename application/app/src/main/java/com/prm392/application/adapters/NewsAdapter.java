package com.prm392.application.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.application.R;
import com.prm392.application.activities.NewsDetailActivity;
import com.prm392.application.models.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

        private List<News> newsList = new ArrayList<>();
        private Context context;
        public NewsAdapter(Context context) {
            this.context = context;
        }
        public static class NewsViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public TextView content;
            public ImageView imageView;

            public NewsViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                content = itemView.findViewById(R.id.content);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.title.setText(news.getTitle());
            holder.content.setText(news.getContent());

            if (news.getImage() != null && !news.getImage().isEmpty()) {
                Picasso.get().load(news.getImage()).into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.drawable.placeholder); // Use a placeholder image
            }

            // Handle click event
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("title", news.getTitle());
                intent.putExtra("content", news.getContent());
                intent.putExtra("image", news.getImage());
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        public void setNewsList(List<News> newsList) {
            this.newsList = newsList;
            notifyDataSetChanged();
        }
}
