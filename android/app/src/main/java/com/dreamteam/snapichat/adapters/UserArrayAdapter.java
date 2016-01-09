package com.dreamteam.snapichat.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.snapichat.R;
import com.dreamteam.snapichat.entities.User;
import com.dreamteam.snapichat.utils.Utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserArrayAdapter extends ArrayAdapter {

    private static final Map<String, Bitmap> USER_IMAGES = new HashMap<>();

    public UserArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_frined, null);
        }

        User p = getItem(position);

        if (p != null) {
            ImageView img = (ImageView) v.findViewById(R.id.user_img);
            TextView name = (TextView) v.findViewById(R.id.user_name);

            if (img != null) {
                String url = Utils.getHttpURL() + "profileImage?uid=" + p.getId();
                new DownloadImageTask(img, p.getId()).execute(url);
            }

            if (name != null) {
                name.setText(p.getUsername());
            }
        }

        return v;
    }

    @Override
    public User getItem(int position) {
        return (User) super.getItem(position);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String uid;

        public DownloadImageTask(ImageView bmImage, String uid) {
            this.bmImage = bmImage;
            this.uid = uid;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            if(USER_IMAGES.get(uid) != null) {
                return USER_IMAGES.get(uid);
            }
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                USER_IMAGES.put(uid, mIcon11);
            } catch (Exception e) {
                Log.e("Image Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
