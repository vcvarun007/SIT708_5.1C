package com.example.itubeapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylist extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_playlist);

        recyclerView = findViewById(R.id.myRecyclerView);
        List<String> urls = getURLs();
        UrlAdapter urlAdapter = new UrlAdapter(urls);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(urlAdapter);
    }

    public List<String> getURLs() {
        List<String> urls = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_URLS,
                new String[]{DatabaseHelper.COLUMN_URL},
                null, null, null, null, null);

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_URL));
                    urls.add(url);
                }
            }
            cursor.close();
        }
        db.close();
        return urls;
    }

    private class UrlAdapter extends RecyclerView.Adapter<UrlAdapter.UrlViewHolder> {

        private List<String> urlList;

        public UrlAdapter(List<String> urlList) {
            this.urlList = urlList;
        }

        @NonNull
        @Override
        public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.url_item, parent, false);
            return new UrlViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {
            String url = urlList.get(position);
            holder.bind(url);
        }

        @Override
        public int getItemCount() {
            return urlList.size();
        }

        // ViewHolder class
        public class UrlViewHolder extends RecyclerView.ViewHolder {
            TextView urlTV;

            public UrlViewHolder(@NonNull View itemView) {
                super(itemView);
                urlTV = itemView.findViewById(R.id.textViewUrl);
            }

            public void bind(String url) {
                urlTV.setText(url);
            }
        }
    }
}