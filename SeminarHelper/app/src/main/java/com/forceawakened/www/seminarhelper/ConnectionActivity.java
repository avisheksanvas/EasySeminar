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
    private EditText emailEt;
    private Socket socket;
    private String serverAddress;
    private String serverPort;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        serverAddressEt = (EditText) findViewById(R.id.server_address);
        serverPortEt = (EditText) findViewById(R.id.server_port);
        emailEt = (EditText) findViewById(R.id.email);
        connectBtn = (Button) findViewById(R.id.connect);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverAddress = String.valueOf(serverAddressEt.getText());
                serverPort = String.valueOf(serverPortEt.getText());
                email = String.valueOf(emailEt.getText());
                serverAddress="192.168.0.14";
                serverPort="1342";
                email="bittu";
                if (email == null || "".equals(email)) {
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
                String ip = String.valueOf(socket.getInetAddress()).substring(1);
                System.out.println(ip);
                SocketHandler.send("USER:" + email + ":" + ip);
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
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            } else {
                Toast.makeText(connectBtn.getContext(), "Connection Failed! Retry.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
