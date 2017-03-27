package com.forceawakened.www.seminarhelper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ConnectionActivity extends AppCompatActivity {
    private Button connectBtn;
    private EditText serverAddressEt;
    private EditText serverPortEt;
    private EditText emailEt;
    private EditText passwordEt;
    private Socket socket;
    private String serverAddress;
    private String serverPort;
    private String email;
    private String password;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        serverAddressEt = (EditText) findViewById(R.id.server_address);
        serverPortEt = (EditText) findViewById(R.id.server_port);
        emailEt = (EditText) findViewById(R.id.email);
        passwordEt = (EditText) findViewById(R.id.password);
        connectBtn = (Button) findViewById(R.id.connect);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverAddress = String.valueOf(serverAddressEt.getText());
                serverPort = String.valueOf(serverPortEt.getText());
                email = String.valueOf(emailEt.getText());
                password = String.valueOf(passwordEt.getText());
                if (email == null || "".equals(email)) {
                    Toast.makeText(connectBtn.getContext(), "Enter Your Email.", Toast.LENGTH_SHORT).show();
                }
                else if (serverAddress != null && !"".equals(serverAddress) && serverPort != null && !"".equals(serverPort)) {
                    Connect connection = new Connect();
                    connection.execute(serverAddress, serverPort);
                }
            }
        });
    }

    public class Connect extends AsyncTask<String, Void, Integer> {

        Integer result = 0;
        @Override
        protected Integer doInBackground(String... params) {
            try {
                socket = new Socket(params[0], Integer.parseInt(params[1]));
                SocketHandler.setSocket(socket);
                SocketHandler.send("USER:" + email + ":" + password);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = br.readLine();
                if("NO".equals(line)){
                    result = 1;
                }
                else if("YES".equals(line)){
                    result = 2;
                }
                else{
                    //error

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result > 0) {
                Toast.makeText(connectBtn.getContext(), "Connected To Server.", Toast.LENGTH_SHORT).show();
                if (result == 1) {
                    Intent intent = new Intent(connectBtn.getContext(), MainActivity.class);
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                }
                else if (result == 2){
                    Intent intent = new Intent(connectBtn.getContext(), MicActivity.class);
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(connectBtn.getContext(), "Connection Failed! Retry.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
