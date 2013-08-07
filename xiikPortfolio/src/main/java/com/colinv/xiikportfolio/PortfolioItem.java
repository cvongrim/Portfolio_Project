package com.colinv.xiikportfolio;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

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
            String portfolio_title = extras.getString("portfolio_title");
            String portfolio_excerpt = extras.getString("portfolio_excerpt");
            String port_img_url = extras.getString("port_img_url");

            TextView textViewTitle = (TextView) findViewById(R.id.textViewTitle);
            TextView textViewExcerpt = (TextView) findViewById(R.id.textViewExcerpt);
            //TextView textViewUrl = (TextView) findViewById(R.id.textViewUrl);
            ImageView imageViewMain = (ImageView) findViewById(R.id.imageViewMain);

            textViewTitle.setText(portfolio_title);
            textViewExcerpt.setText(portfolio_excerpt);
            //textViewUrl.setText(port_img_url);
           // new DownloadImageTask((ImageView) findViewById(R.id.imageViewMain)).execute(port_img_url);

            ImageView imgView = (ImageView) findViewById(R.id.imageViewMain);
            ImageLoader imgLoader = new ImageLoader(this);
            imgLoader.DisplayImage(port_img_url, imgView);
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
