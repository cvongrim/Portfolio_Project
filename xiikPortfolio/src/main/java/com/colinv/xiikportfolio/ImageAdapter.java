package com.colinv.xiikportfolio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {
    private Context context;
    ArrayList<ArrayList> mobileValues;

    public ImageAdapter(Context context, ArrayList<ArrayList> mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            //gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mobile, null);

            // set value into TextView
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);

            ArrayList<String> portArray = mobileValues.get(position);
            textView.setText(portArray.get(0));


            String port_img_url = portArray.get(2);
            // set image based on selected text
            new DownloadImageTask((ImageView) gridView.findViewById(R.id.grid_item_image)).execute(port_img_url);
            /*ImageView imgView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            ImageLoader imgLoader = new ImageLoader(gridView.getContext());
            imgLoader.DisplayImage(port_img_url, imgView); */


            // ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

           // imageView.setImageResource(R.drawable.android_logo);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
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

    @Override
    public int getCount() {
        return mobileValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}