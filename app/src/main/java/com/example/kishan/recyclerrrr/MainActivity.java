package com.example.kishan.recyclerrrr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
 
public class MainActivity extends AppCompatActivity {
    private List<Data> movieList = new ArrayList<Data>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
 
        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
 
        prepareMovieData();
    }
 
    private void prepareMovieData() {
        Data movie = new Data("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
 
        movie = new Data("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
 
        movie = new Data("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);
 
        movie = new Data("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);
 
        movie = new Data("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);
 
        movie = new Data("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);
 
        movie = new Data("Up", "Animation", "2009");
        movieList.add(movie);
 
        movie = new Data("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);
 
        movie = new Data("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);
 
        movie = new Data("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);
 
        movie = new Data("Aliens", "Science Fiction", "1986");
        movieList.add(movie);
 
        movie = new Data("Chicken Run", "Animation", "2000");
        movieList.add(movie);
 
        movie = new Data("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);
 
        movie = new Data("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);
 
        movie = new Data("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);
 
        movie = new Data("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);
 
        mAdapter.notifyDataSetChanged();
    }
}