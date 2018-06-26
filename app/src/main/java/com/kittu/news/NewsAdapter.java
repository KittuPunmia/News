package com.kittu.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.kittu.news.R.id.newsDes;
import static com.kittu.news.R.id.newsTit;

/**
 * Created by user on 23-03-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
private Context context;
    private List<Article> items;

    public NewsAdapter(Context context, List<Article> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.news_item,parent,false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
final Article item=items.get(position);
        holder.newsTit.setText(item.getTitle());
        holder.newsDes.setText(item.getDescription());
        Glide.with(context).load(item.getUrlToImage()).into(holder.newsImage);
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(context,DetailNews.class);
        i.putExtra("url",item.getUrl());
        context.startActivity(i);
    }
});
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTit;
        TextView newsDes;
        public NewsViewHolder(View itemView) {
            super(itemView);
            newsImage=(ImageView) itemView.findViewById(R.id.newsImage);
            newsTit=(TextView) itemView.findViewById(R.id.newsTit);
            newsDes=(TextView) itemView.findViewById(R.id.newsDes);

        }
    }
}
