package com.dreamteam.snapichat.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dreamteam.snapichat.MainActivity;
import com.dreamteam.snapichat.R;
import com.dreamteam.snapichat.entities.User;
import com.dreamteam.snapichat.adapters.UserArrayAdapter;
import com.dreamteam.snapichat.utils.DistanceFinder;
import com.dreamteam.snapichat.utils.JSONParser;
import com.dreamteam.snapichat.utils.Utils;
import com.dreamteam.snapichat.utils.WebSocketManager;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {

    private final static int FID = 1;

    private List<User> friends;
    private UserArrayAdapter adapter;
    private List<String> myFriendUIDList;

    public FriendsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().registerReceiver(new FragmentReceiver(), new IntentFilter("fragmentUpdater"));

        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView list = (ListView) view.findViewById(R.id.listView);
        setupFriendsListView(list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                myFriendUIDList = new ArrayList<>();
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("uid", WebSocketManager.getWebSocketManager().getUID()));

                String url = Utils.getHttpURL() + "listFriends?";

                JSONParser jsonParser = new JSONParser();
                JSONObject response = jsonParser.makeHttpRequest(url, "POST", params);
                try {
                    JSONArray friendsJSON = response.getJSONArray("friends");
                    for(int i=0; i<friendsJSON.length(); i++) {
                        JSONObject f = friendsJSON.getJSONObject(i);
                        String fid = f.getString("id");
                        myFriendUIDList.add(fid);
                    }
                } catch (JSONException ex) {
                    Log.e("Friends List", ex.toString());
                }

                final WebSocketManager socketManager = WebSocketManager.getWebSocketManager();
                WebSocket ws = socketManager.getWebSocket();

                ws.addListener(new WebSocketAdapter() {
                    @Override
                    public void onTextMessage(WebSocket websocket, String text) throws Exception {
                        JSONObject message = new JSONObject(text);
                        String action = message.get("action").toString();

                        if(action.equals("LIST")) {
                            JSONArray onlineUsers = (JSONArray) message.get("users");
                            friends.clear();

                            SharedPreferences prefs = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE);
                            String longitudeStr = prefs.getString("Longitude", "0");
                            String latitudeStr = prefs.getString("Latitude", "0");
                            double mylong = Double.parseDouble(longitudeStr);
                            double myLat = Double.parseDouble(latitudeStr);

                            for (int i = 0; i < onlineUsers.length(); i++) {
                                JSONObject userJSON = (JSONObject) onlineUsers.get(i);
                                String uid = userJSON.getString("uid");
                                String username = userJSON.getString("username");

                                double longitude = userJSON.getDouble("longitude");
                                double latitude = userJSON.getDouble("latitude");

                                // Continue without adding me to the list
                                if (uid.equals(socketManager.getUID())) {
                                    continue;
                                }

                                double distance = DistanceFinder.distance(latitude, myLat, longitude, mylong, 0, 0);
                                Log.d("distance", username + " " + distance);

                                if(!myFriendUIDList.contains(uid) && distance > 1000) {
                                    continue;
                                }
                                User u = new User(uid, username);
                                friends.add(u);
                            }

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
            }
        }).start();
    }

    private void setupFriendsListView(ListView list) {
        friends = new ArrayList<>();
        adapter = new UserArrayAdapter(
                getContext(), R.layout.row_frined, friends);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                User user = adapter.getItem(position);

                Intent data = new Intent("fragmentUpdater");
                data.putExtra("toUID", user.getId());
                data.putExtra("toUsername", user.getUsername());
                data.putExtra("FID", 2);
                getActivity().sendBroadcast(data);

                ((MainActivity) getActivity()).navigateToChat();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser) {
            forceUpdateFriends();
        }
    }

    private void forceUpdateFriends() {
        try {
            Log.d("Force Update", "update friends list");
            WebSocket socket = WebSocketManager.getWebSocketManager().getWebSocket();
            JSONObject json = new JSONObject();
            json.put("action", "LIST");
            socket.sendText(json.toString());
        } catch (JSONException e) {
            Log.e("Force List", e.toString());
        }
    }

    public class FragmentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle data = intent.getExtras();
            int fragmentID = data.getInt("FID");
            if(fragmentID != FID) {
                return;
            }

            Log.d("Friends Fragment", "message received");
        }
    }
}
