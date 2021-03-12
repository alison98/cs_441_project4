package com.example.project4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4.MyRecyclerViewAdapter;
import com.example.project4.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ArrayList<Integer[]> colors;
    int recentPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        colors = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            colors.add(getRandomColor());
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, colors);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void add(View view){
        Integer[] newColor = getRandomColor();
        int insertIndex = colors.size();
        colors.add(insertIndex, newColor);
        adapter.notifyItemInserted(insertIndex);
    }

    public void remove(View view){
        if(colors.size() == 0){
            return;
        }
        colors.remove(recentPos);
        adapter.notifyItemRemoved(recentPos);
    }

    public void randomize(View view){
        int size = colors.size();
        colors.clear();
        for(int i = 0; i < size; i++){
            colors.add(getRandomColor());
        }
        adapter.notifyDataSetChanged();
    }

    public Integer[] getRandomColor(){
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256);
        int b = (int)(Math.random() * 256);
        return new Integer[]{r, g, b};
    }

    @Override
    public void onItemClick(View view, int position) {
        recentPos = position;
    }
}