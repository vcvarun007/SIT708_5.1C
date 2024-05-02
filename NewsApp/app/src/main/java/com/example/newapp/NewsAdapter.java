package com.example.newapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.UrlViewHolder> {
    private List<NewsItem> newsList;
    private OnItemClickListener listener;
    private int res;

    public NewsAdapter(List<NewsItem> newsList, OnItemClickListener listener, int res) {
        this.newsList = newsList;
        this.listener = listener;
        this.res = res;
    }

    @NonNull
    @Override
    public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(res, parent, false);
        return new UrlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {
        NewsItem item = newsList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class UrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewHeadline;
        TextView textViewDescription;

        public UrlViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_news_vertical);
            textViewHeadline = itemView.findViewById(R.id.tv_news_title_vertical);
            textViewDescription = itemView.findViewById(R.id.tv_news_desc_vertical);
            itemView.setOnClickListener(this);
        }

        public void bind(NewsItem item) {
            imageView.setImageResource(item.getImageResId());
            textViewHeadline.setText(item.getHeadline());
            textViewDescription.setText(item.getDescription());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(newsList.get(position));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem newsItem);
    }
}