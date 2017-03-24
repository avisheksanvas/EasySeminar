package com.forceawakened.www.seminarhelper;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by forceawakened on 24/03/17.
 */

public class SocketService extends Service {
    String filename1 = "home.txt";
    String filename2 = "query.txt";
    String filename3 = "filelist.txt";
    Socket socket;
    Integer countMsg = 0;
    OutputStream out1, out2, out3;

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    private final IBinder myBinder = new LocalBinder() ;

    public class LocalBinder extends Binder {
        public SocketService getService() {
            return SocketService.this;
        }
    }

    public void IsBoundable(){
        Toast.makeText(this,"I bind like butter", Toast.LENGTH_LONG).show();
    }

    public void sendMessage(String msg){
        try {
            out1.write(msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendQuery(String msg){
        try {
            out2.write(msg.getBytes());
            displayNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String msg){
        try {
            out3.write(msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Seminar Helper: Unread Messages")
                        .setContentText("You have " + countMsg + " unread messages.");
        countMsg = 0;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Runnable connect = new connectSocket();
        System.out.println("The service thread is about to start.") ;
        new Thread(connect).start();
        return START_STICKY;
    }

    class connectSocket implements Runnable {
        @Override
        public void run() {
            System.out.println("Service thread created!") ;
            try {
                socket = SocketHandler.getSocket();
                System.out.println("Socket: " + String.valueOf(socket));
                try{
                    out1 = openFileOutput(filename1, Context.MODE_APPEND);
                    out2 = openFileOutput(filename2, Context.MODE_APPEND);
                    out3 = openFileOutput(filename3, Context.MODE_APPEND);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String text, key, value;
                    while((text = in.readLine()) != null) {
                        key = text.substring(0, 4);
                        value = text.substring(5);
                        value += '\n';
                        if("EXIT".equals(key)) {
                            break;
                        }
                        else if("TEXT".equals(key)){
                            sendMessage(value);
                        }
                        else if("QUER".equals(key)){
                            sendQuery(value);
                            ++countMsg;
                        }
                        else if("FILE".equals(key)){
                            sendFile(value);
                        }
                    }
                    out1.close();
                    out2.close();
                    out3.close();
                }
                catch (Exception e) {
                    System.out.println("Service: while reading/writing socket.");
                    e.printStackTrace();
                }
            }
            catch (Exception e ) {
                System.out.println("Service: error in service thread.");
                e.printStackTrace();
            }
        }
    }
}