package com.dreamteam.snapichat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dreamteam.snapichat.entities.Message;
import com.dreamteam.snapichat.R;
import com.dreamteam.snapichat.adapters.MessageArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class UserChatFragment extends Fragment {

    private ListView chatList;
    private final List<Message> messages = new ArrayList<>();
    private MessageArrayAdapter messagesAdapter;

    public UserChatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatList = (ListView) view.findViewById(R.id.chat_list);
        messagesAdapter = new MessageArrayAdapter(getContext(), R.layout.row_chat, messages);
        chatList.setAdapter(messagesAdapter);
    }

    public void addMessage(final String msgText, final boolean me, final String img) {
        if(chatList == null) {
            return;
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Message m = new Message(msgText, me);
                if(!img.equals("")) {
                    m.setImage(img);
                }
                messages.add(m);
                messagesAdapter.notifyDataSetChanged();
            }
        });
    }
}
