package com.dreamteam.snapichat.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dreamteam.snapichat.R;
import com.dreamteam.snapichat.utils.WebSocketManager;
import com.dreamteam.snapichat.adapters.ChatViewPagerAdapter;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class ChatFragment extends Fragment {

    private final static int FID = 2;
    private final static int PICK_PHOTO = 1;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChatViewPagerAdapter tabAdapter;

    private Map<String, UserChatFragment> chatTabs;

    public ChatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().registerReceiver(new FragmentReceiver(), new IntentFilter("fragmentUpdater"));

        chatTabs = new HashMap<>();
        tabAdapter = new ChatViewPagerAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.chat_viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                WebSocketManager socketManager = WebSocketManager.getWebSocketManager();
                socketManager.setActiveChatUID(tabAdapter.getUID(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setAdapter(tabAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.chat_tabs);
        tabLayout.setupWithViewPager(viewPager);

        final EditText editText = (EditText) view.findViewById(R.id.message_field);

        Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebSocketManager socketManager = WebSocketManager.getWebSocketManager();
                String text = editText.getText().toString();

                if (socketManager.getWebSocket() == null) {
                    return;
                }

                try {
                    JSONObject obj = new JSONObject();
                    obj.put("action", "MESSAGE");
                    obj.put("message", text);
                    obj.put("to", socketManager.getActiveChatUID());
                    obj.put("from", socketManager.getUID());
                    obj.put("img", "");
                    obj.put("from_username", socketManager.getUsername());

                    socketManager.getWebSocket().sendText(obj.toString());
                } catch (JSONException ex) {
                    Log.e("On Send", ex.toString());
                }

                editText.setText("");
            }
        });

        Button addImageButton = (Button) view.findViewById(R.id.button3);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_PHOTO);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                addWebSocketListener();
            }
        }).start();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Log.e("Select Image", "Error due to image pick");
                return;
            }
            try {
//                System.out.println(data.getDataString());
//                File f = new File(data.getDataString().replace("file://", ""));
//                byte[] b = FileUtils.readFileToByteArray(f);
//                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                InputStream inputStream = getContext().getContentResolver().openInputStream(data.getData());
                if(inputStream == null) {
                    Toast.makeText(getContext(), "An error occurred, while reading image", Toast.LENGTH_LONG).show();
                    return;
                }
                String encodedImage = Base64.encodeToString(IOUtils.toByteArray(inputStream), Base64.DEFAULT);
                encodedImage = "data:image/webp;base64," + encodedImage;

                WebSocketManager socketManager = WebSocketManager.getWebSocketManager();

                if (socketManager.getWebSocket() == null) {
                    return;
                }

                try {
                    JSONObject obj = new JSONObject();
                    obj.put("action", "MESSAGE");
                    obj.put("message", "");
                    obj.put("to", socketManager.getActiveChatUID());
                    obj.put("from", socketManager.getUID());
                    obj.put("img", encodedImage);
                    obj.put("from_username", socketManager.getUsername());

                    socketManager.getWebSocket().sendText(obj.toString());
                } catch (JSONException ex) {
                    Log.e("On Send", ex.toString());
                }
            } catch (Exception ex) {
                Log.e("Select Image", ex.toString());
            }
        }
    }

    public void addWebSocketListener() {
        final WebSocketManager socketManager = WebSocketManager.getWebSocketManager();
        WebSocket ws = socketManager.getWebSocket();

        ws.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket websocket, String text) throws Exception {
                JSONObject message = new JSONObject(text);
                String action = message.get("action").toString();

                if (action.equals("MESSAGE")) {
                    String sender = message.get("from_username").toString();
                    String senderID = message.getString("from");
                    String msg = message.get("message").toString();
                    String img = message.get("img").toString();

                    boolean me = false;

                    if (senderID.equals(socketManager.getUID())) {
                        senderID = message.getString("to");
                        me = true;
                    }

                    UserChatFragment fragment;

                    // If tab exists use it, else create new
                    if (chatTabs.containsKey(senderID)) {
                        fragment = chatTabs.get(senderID);
                    } else {
                        fragment = getFragment(senderID);
                        chatTabs.put(senderID, fragment);
                        tabAdapter.addFragment(fragment, sender, senderID);
                        tabAdapter.notifyDataSetChanged();
                        tabLayout.setupWithViewPager(viewPager);
                    }

                    String formattedText = sender + ": " + msg;
                    fragment.addMessage(formattedText, me, img);
                }
            }
        });
    }

    public class FragmentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle data = intent.getExtras();
            int fragmentID = data.getInt("FID");
            if (fragmentID != FID) {
                return;
            }

            String uid = data.getString("toUID");
            String username = data.getString("toUsername");

            if (!chatTabs.containsKey(uid)) {
                UserChatFragment fragment = getFragment(uid);
                chatTabs.put(uid, fragment);
                tabAdapter.addFragment(fragment, username, uid);
                tabAdapter.notifyDataSetChanged();
                tabLayout.setupWithViewPager(viewPager);
            }

            viewPager.setCurrentItem(tabAdapter.getPositionByUID(uid));
            WebSocketManager.getWebSocketManager().setActiveChatUID(uid);
        }
    }

    private UserChatFragment getFragment(String toUID) {
        UserChatFragment myFragment = new UserChatFragment();
        Bundle data = new Bundle();
        data.putString("toUID", toUID);
        myFragment.setArguments(data);
        return myFragment;
    }
}
