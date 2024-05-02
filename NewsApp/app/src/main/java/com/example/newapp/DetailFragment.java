package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    public static DetailFragment newInstance(NewsItem item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
//        args.putParcelable("item", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ImageView imageView = view.findViewById(R.id.imageView2);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_description = view.findViewById(R.id.tv_description);
        RecyclerView recyclerView = view.findViewById(R.id.rv_vertical_news);

        NewsItem item = getArguments().getParcelable("item");
        if (item != null) {
            imageView.setImageResource(item.getImageResId());
            tv_title.setText(item.getHeadline());
            tv_description.setText(item.getDescription());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(createVerticalNewsAdapter());
        return view;
    }

    private NewsAdapter createVerticalNewsAdapter() {
       List<NewsItem> topStories = new ArrayList<>();
        topStories.add(new NewsItem(R.drawable.first, "New Study Shows Benefits of Meditation", "New Study Shows Benefits of Meditation."));
        topStories.add(new NewsItem(R.drawable.second, "Tech Giant Unveils Latest Smartphone Model", "The highly anticipated release of the newest smartphone from a leading tech company promises groundbreaking features and enhanced user experience."));
        topStories.add(new NewsItem(R.drawable.third, "Local Community Organizes Charity Event", "Residents come together to organize a charity event aimed at raising funds for local shelters and supporting underprivileged families in the area."));
        topStories.add(new NewsItem(R.drawable.fourth, "Scientists Discover Potential Treatment for Alzheimer's", "A prestigious art gallery hosts an exhibition featuring works by up-and-coming artists, providing a platform for emerging talent to showcase their creativity."));
        topStories.add(new NewsItem(R.drawable.fifth, "Art Exhibition Showcases Emerging Talent", "Researchers make a significant breakthrough in Alzheimer's research, identifying a potential treatment that could slow down the progression of the disease."));
        topStories.add(new NewsItem(R.drawable.sixth, "Environmental Initiative Aims to Reduce Plastic Waste", "A new environmental initiative launches a campaign to raise awareness about plastic pollution and promote sustainable alternatives to reduce plastic waste."));
        topStories.add(new NewsItem(R.drawable.seventh, "Celebrity Chef Opens Trendy Restaurant Downtown", "Renowned chef expands culinary empire with the opening of a chic downtown restaurant, offering innovative dishes and a unique dining experience."));
        topStories.add(new NewsItem(R.drawable.eighth, "World Leaders Gather for Climate Summit", "Global leaders convene for a climate summit to discuss urgent measures to address climate change and commit to sustainable practices for a greener future."));
        topStories.add(new NewsItem(R.drawable.ninth, "Innovative Startup Revolutionizes Healthcare Industry", "A groundbreaking healthcare startup introduces innovative technology that promises to revolutionize patient care and improve healthcare outcomes."));
        topStories.add(new NewsItem(R.drawable.tenth, "Local Sports Team Clinches Championship Victory", "Fans celebrate as the hometown sports team secures a thrilling championship victory, marking a historic moment in the team's journey to success."));


        return new NewsAdapter(topStories, null, R.layout.news_vertical);
    }
}