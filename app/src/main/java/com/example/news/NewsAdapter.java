package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.pojo.Article;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {

    private Context mContext;
    private List<Article> ArticleList = new ArrayList<>();

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_item, viewGroup, false);

        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewViewHolder viewHolder, int i) {
//        TextView News_title , desc , author , publishAt, source, time ;

        viewHolder.News_title.setText(ArticleList.get(i).getTitle());
        viewHolder.desc.setText(ArticleList.get(i).getDescription());
        viewHolder.time.setText("\u2022"+Utils.DateToTimeFormat(ArticleList.get(i).getPublishedAt()));
        viewHolder.source.setText(ArticleList.get(i).getSource().getName());
        viewHolder.publishAt.setText(Utils.DateFormat(ArticleList.get(i).getPublishedAt()));
//        viewHolder.author.setText(ArticleList.get(i).getAuthor());

        Glide.with(mContext).load(ArticleList.get(i).getUrlToImage())
                .placeholder(R.drawable.ic_placeholder)
                .into(viewHolder.News_img);
    }

    @Override
    public int getItemCount() {
        return ArticleList.size();
    }

    public void setList(List<Article> ArticleList) {
        this.ArticleList = ArticleList;
        notifyDataSetChanged();
    }


    public class NewViewHolder extends RecyclerView.ViewHolder {

        ImageView News_img ;
        TextView News_title , desc , author , publishAt, source, time ;
//        ProgressBar progressBar ;

        public NewViewHolder(View view) {
            super(view);

            News_img = view.findViewById(R.id.news_image);
            News_title=view.findViewById(R.id.news_title);
            desc=view.findViewById(R.id.news_desc);
//            author=view.findViewById(R.id.author);
            publishAt=view.findViewById(R.id.publish_At);
            source=view.findViewById(R.id.source);
            time=view.findViewById(R.id.time);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        Article clicked = ArticleList.get(pos);

                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra("url", clicked.getUrl());
                        intent.putExtra("title", clicked.getTitle());
                        intent.putExtra("img", clicked.getUrlToImage());
                        intent.putExtra("date", clicked.getPublishedAt());
                        intent.putExtra("source", clicked.getSource().getName());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
//                        Toast.makeText(context, " " + clicked.getId(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(context, " "+xx, Toast.LENGTH_SHORT).show();

                    }

                }
            });



        }
    }
}