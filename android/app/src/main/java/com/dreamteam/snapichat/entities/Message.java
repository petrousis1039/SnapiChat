package com.dreamteam.snapichat.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Message {

    Bitmap image;
    Bitmap thumbnail;
    String text;
    boolean me;

    public Message(String text, boolean me) {
        this.text = text;
        this.me = me;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImage(String blob) {
        String imageInfoBytes = blob.substring(0, blob.indexOf(","));
        String imageDataBytes = blob.substring(blob.indexOf(",")+1);

        InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
        Bitmap bm = BitmapFactory.decodeStream(stream);
        this.image = bm;

        bm = Bitmap.createScaledBitmap(bm, 150, 150, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(imageInfoBytes.contains("png")) {
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        } else {
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }
        byte[] imageData = baos.toByteArray();
        bm = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);

        this.thumbnail = bm;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }

    @Override
    public String toString() {
        return text;
    }
}
