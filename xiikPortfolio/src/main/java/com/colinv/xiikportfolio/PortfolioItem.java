package com.colinv.xiikportfolio;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Colinv on 8/5/13.
 */
public class PortfolioItem extends Gridview{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_item);

        // Get Data of what gallery item was pushed
        Bundle extras = getIntent().getExtras();
        if(extras != null){

            // Get our variables from the Intent
            String portfolioTitle = extras.getString("portfolioTitle");
            String portfolioExcerpt = extras.getString("portfolioExcerpt");
            String portfolioImageUrl = extras.getString("portfolioImageUrl");

            // Assign references to our TextViews
            TextView textViewTitle = (TextView) findViewById(R.id.textViewTitle);
            TextView textViewExcerpt = (TextView) findViewById(R.id.textViewExcerpt);

            // Set the variables as the value of our TextViews
            textViewTitle.setText(portfolioTitle);
            textViewExcerpt.setText(portfolioExcerpt);

            // Set up our image caching
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .build();

            // Create a reference to our ImageView
            ImageView imgView = (ImageView) findViewById(R.id.imageViewMain);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(this)); // Initialize the ImageLoader
            imageLoader.displayImage(portfolioImageUrl, imgView, options);  // Load the image into our ImageView


        }

        // set up button listener
        Button startButton = (Button) findViewById(R.id.buttonBack);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
