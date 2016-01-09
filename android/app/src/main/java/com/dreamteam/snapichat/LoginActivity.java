package com.dreamteam.snapichat;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dreamteam.snapichat.utils.JSONParser;
import com.dreamteam.snapichat.utils.Utils;
import com.dreamteam.snapichat.utils.WebSocketManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private JSONParser jParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        jParser = new JSONParser();

        final EditText usernameField = (EditText) findViewById(R.id.editText);
        final EditText passwordField = (EditText) findViewById(R.id.editText2);

        Button signInButton = (Button) findViewById(R.id.button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                new Login().execute(username, password);
            }
        });
    }

    private class Login extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... args) {
            String username = args[0];
            String password = args[1];

            List<NameValuePair> params = new ArrayList<>();
            System.out.println(username + " " + password);
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            String url = Utils.getHttpURL() + "api/login?";

            JSONObject json = jParser.makeHttpRequest(url, "GET", params);

            Log.d("Login JSON Response", json.toString());

            try {
                String success = json.getString("success");
                if (success.equals("1")) {
                    String responseUID = json.getString("uid");
                    String responseUsername = json.getString("username");

                    WebSocketManager.initWebSocketManager(responseUID, responseUsername);
                    WebSocketManager.getWebSocketManager().connect();

                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    finish();
                }
            } catch (JSONException e) {
                Log.e("Login JSON", e.toString());
                return false;
            }

            return true;
        }


    }
}
