package com.forceawakened.www.seminarhelper;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by forceawakened on 24/3/17.
 */
public class SocketHandler {
    private static Socket socket;
    private static PrintStream ps;

    public static Socket getSocket(){
        return socket;
    }

    public static void setSocket(Socket socket){
        if(SocketHandler.socket != null){
            close();
        }
        SocketHandler.socket = socket;
        try {
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(String msg){
        ps.println(msg);
    }

    public static void close(){
        try {
            ps.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
