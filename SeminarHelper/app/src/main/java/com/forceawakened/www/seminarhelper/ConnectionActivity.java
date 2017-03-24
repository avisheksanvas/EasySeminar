package com.forceawakened.www.seminarhelper;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionActivity extends AppCompatActivity {
    private Button connectBtn;
    private EditText serverAddressEt;
    private EditText serverPortEt;
    private EditText usernameEt;
    private Socket socket;
    private String serverAddress;
    private String serverPort;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        serverAddressEt = (EditText) findViewById(R.id.server_address);
        serverPortEt = (EditText) findViewById(R.id.server_port);
        usernameEt = (EditText) findViewById(R.id.user_name);
        connectBtn = (Button) findViewById(R.id.connect);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverAddress = String.valueOf(serverAddressEt.getText());
                serverPort = String.valueOf(serverPortEt.getText());
                username = String.valueOf(usernameEt.getText());
                serverAddress="192.168.0.14";
                serverPort="8889";
                username="forceawakened";
                if (username == null || "".equals(username)) {
                    Toast.makeText(connectBtn.getContext(), "Enter Your Username.", Toast.LENGTH_SHORT).show();
                } else if (serverAddress != null && !"".equals(serverAddress) && serverPort != null && !"".equals(serverPort)) {
                    Connect connection = new Connect();
                    connection.execute(serverAddress, serverPort);
                }
            }
        });
    }

    public class Connect extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            try {
                System.out.println(params[0] + "  " + params[1]);
                socket = new Socket(params[0], Integer.parseInt(params[1]));
                SocketHandler.setSocket(socket);
                SocketHandler.send("USER:forceawakened");
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            boolean isConnected = result;
            if (isConnected) {
                Toast.makeText(connectBtn.getContext(), "Connected To Server.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(connectBtn.getContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(connectBtn.getContext(), "Connection Failed! Retry.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
