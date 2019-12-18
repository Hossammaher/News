package com.example.news;


import android.app.SearchManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.news.pojo.Article;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NewsViewModel newsViewModel;
    NewsAdapter newsAdapter;
    RecyclerView newsRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    CircleImageView sport, business, general, health, science, technology;
    String category = "general";
    SearchView searchView ;
    RelativeLayout noResultLayout ;
    ImageView noResultImage ;
    TextView noResultTitle ,noResultSubTitle ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sport = findViewById(R.id.sport);
        business = findViewById(R.id.business);
        general = findViewById(R.id.general);
        health = findViewById(R.id.health);
        science = findViewById(R.id.science);
        technology = findViewById(R.id.technology);
        noResultLayout=findViewById(R.id.noResultLayout);
        noResultImage=findViewById(R.id.noResultImage);
        noResultTitle=findViewById(R.id.noResultTitle);
        noResultSubTitle=findViewById(R.id.noResultSubtitle);


        newsAdapter = new NewsAdapter(this);
        newsRecyclerView = findViewById(R.id.news_Recycler);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getTopNews(category);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(newsAdapter);

        if (!haveNetwork()){
            Toasty.error(MainActivity.this,"no internet").show();
//            swipeRefreshLayout.setRefreshing(false);
//            swipeRefreshLayout.setEnabled(false);
            noResultImage.setVisibility(View.VISIBLE);
            SwipeRefresh(true);
        }

        else {
//            swipeRefreshLayout.setEnabled(true);
            SwipeRefresh(true);

            newsViewModel.NewsMutableLiveData.observe(this, new Observer<List<Article>>() {
                @Override
                public void onChanged(List<Article> articles) {
                    newsAdapter.setList(articles);
                    swipeRefreshLayout.setRefreshing(false);
                    if (articles.size() < 1) {

                        noResultLayout.setVisibility(View.VISIBLE);
                        noResultTitle.setText("No Result");
                        noResultSubTitle.setText("Try another Word");
                        newsRecyclerView.setVisibility(View.GONE);

                    } else {
                        noResultLayout.setVisibility(View.GONE);
                        newsRecyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });


            sport.setOnClickListener(this);
            business.setOnClickListener(this);
            general.setOnClickListener(this);
            health.setOnClickListener(this);
            science.setOnClickListener(this);
            technology.setOnClickListener(this);


        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            //action circleImage and make circle bold
            case R.id.business:
                category = "business";
                business.setBorderWidth(9);
                sport.setBorderWidth(3);
                general.setBorderWidth(3);
                science.setBorderWidth(3);
                technology.setBorderWidth(3);
                health.setBorderWidth(3);
                break;
            case R.id.sport:
                category = "sport";
                business.setBorderWidth(3);
                sport.setBorderWidth(9);
                general.setBorderWidth(3);
                science.setBorderWidth(3);
                technology.setBorderWidth(3);
                health.setBorderWidth(3);
                break;
            case R.id.general:
                category = "general";
                business.setBorderWidth(3);
                sport.setBorderWidth(3);
                general.setBorderWidth(9);
                science.setBorderWidth(3);
                technology.setBorderWidth(3);
                health.setBorderWidth(3);
                break;
            case R.id.health:
                category = "health";
                business.setBorderWidth(3);
                sport.setBorderWidth(3);
                general.setBorderWidth(3);
                science.setBorderWidth(3);
                technology.setBorderWidth(3);
                health.setBorderWidth(9);
                break;

            case R.id.science:
                category = "science";
                business.setBorderWidth(3);
                sport.setBorderWidth(3);
                general.setBorderWidth(3);
                science.setBorderWidth(9);
                technology.setBorderWidth(3);
                health.setBorderWidth(3);
                break;
            case R.id.technology:
                category = "technology";
                business.setBorderWidth(3);
                sport.setBorderWidth(3);
                general.setBorderWidth(3);
                science.setBorderWidth(3);
                technology.setBorderWidth(9);
                health.setBorderWidth(3);

                break;
        }

        if (haveNetwork()){ // check internet if true make swipe refresh
            SwipeRefresh(true);
        }else {                  // if false make swipe not refresh
            SwipeRefresh(false);
        }
        newsViewModel.getTopNews(category); // get result depend on category
        newsRecyclerView.setAdapter(newsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // search icon
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        MenuItem search = menu.findItem(R.id.action_search);
         searchView = (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                swipeRefreshLayout.setRefreshing(true);
                newsViewModel.getNewsSearch(s);
                newsRecyclerView.setAdapter(newsAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    protected boolean haveNetwork() {


        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this.getSystemService(MainActivity.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }

    private void SwipeRefresh (boolean swipe){
        swipeRefreshLayout.setRefreshing(swipe);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (haveNetwork()){
                    newsViewModel.getTopNews(category);
                    swipeRefreshLayout.setRefreshing(false);
                    searchView.onActionViewCollapsed();
                    noResultLayout.setVisibility(View.GONE);
                    newsRecyclerView.setVisibility(View.VISIBLE);

                }
                else {


                    swipeRefreshLayout.setRefreshing(false);
                    noResultLayout.setVisibility(View.VISIBLE);
                    noResultTitle.setText("No Internet Connection");
                    noResultSubTitle.setText("Check your Internet");
                    newsRecyclerView.setVisibility(View.GONE);

                }

            }
        });

    }

}
