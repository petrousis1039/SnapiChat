package com.dreamteam.snapichat.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamteam.snapichat.entities.Message;

import java.util.List;

public class MessageArrayAdapter extends ArrayAdapter {

    public MessageArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(com.dreamteam.snapichat.R.layout.row_chat, null);

        final Message p = (Message) getItem(position);

        if (p != null) {
            LinearLayout row = (LinearLayout) v.findViewById(com.dreamteam.snapichat.R.id.chat_row_layout);
            ImageView imageView = (ImageView) v.findViewById(com.dreamteam.snapichat.R.id.chat_message_img);
            TextView text = (TextView) v.findViewById(com.dreamteam.snapichat.R.id.chat_message_text);

            if(row != null) {
                if(!p.isMe())
                    row.setBackgroundColor(ContextCompat.getColor(getContext(), com.dreamteam.snapichat.R.color.colorAccent));
                else
                    row.setBackgroundColor(ContextCompat.getColor(getContext(), com.dreamteam.snapichat.R.color.textColorPrimary));
            }

            if(imageView != null) {
                final Bitmap image = p.getThumbnail();
                if(image != null) {
                    imageView.setImageBitmap(image);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog dialog=new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                            dialog.setContentView(com.dreamteam.snapichat.R.layout.fullscreen_image);
                            ImageView fullImageView = (ImageView) dialog.findViewById(com.dreamteam.snapichat.R.id.imageView2);
                            fullImageView.setImageBitmap(p.getImage());
                            dialog.show();
                        }
                    });
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }

            if (text != null) {
                text.setText(p.getText());
            }
        }

        return v;
    }

    @Override
    public Message getItem(int position) {
        return (Message) super.getItem(position);
    }
}
