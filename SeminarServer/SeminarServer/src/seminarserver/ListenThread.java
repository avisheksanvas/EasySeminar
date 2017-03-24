/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminarserver;

/**
 *
 * @author abhey
 */
import java.awt.event.MouseListener;
import java.net.*;
import java.io.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ListenThread extends Thread  {
    private Socket sock;
    private int number;
    
    public ListenThread(Socket s,int num){
        this.sock = s;
        this.number = number;
    }
    
    public void run(){
        BufferedReader br = null;
        PrintStream p = null;
        String temp;
        try{
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            p = new PrintStream(sock.getOutputStream());
            temp=br.readLine();
            System.out.println(temp);
            if(temp.substring(0,4).compareTo("NAME")==0){
                temp=temp.substring(5,temp.length());
                int index=temp.indexOf(':');
                String email=temp.substring(0,index);
                String IP=temp.substring(index+1,temp.length());
                System.out.println(email+" "+IP);
                System.out.println(LiveSeminar.live);
                LiveSeminar.live.setData(email);
            }
            if(temp.substring(0,4).compareTo("QUES")==0){
                temp=temp.substring(5,temp.length());
                int index=temp.indexOf(':');
                String email=temp.substring(0,index);
                temp=temp.substring(index+1,temp.length());
                index=temp.indexOf('#');
                String IP=temp.substring(0,index);
                String ques=temp.substring(index+1,temp.length());
                LiveSeminar.sbutton[LiveSeminar.live.count]=new Struct(ques,email,IP);
                DefaultListModel model = new DefaultListModel();
                index=LiveSeminar.live.count;
                for(int i=0;i<index;i++){
                   model.addElement(LiveSeminar.sbutton[i].ques);
                }
                model.addElement(ques);
                LiveSeminar.list.setModel(model);
                LiveSeminar.live.count++;
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Error Occurred\n");
        }
    }
}
