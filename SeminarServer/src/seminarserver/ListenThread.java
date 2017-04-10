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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ListenThread extends Thread {

    private Socket sock;
    private int number;

    public ListenThread(Socket s, int num) {
        this.sock = s;
        this.number = number;
    }

    public void run() {

        BufferedReader br = null;
        PrintStream p = null;
        String temp;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            p = new PrintStream(sock.getOutputStream());
            while (true) {
                temp = br.readLine();
                System.out.println(temp);
                if (temp.substring(0, 5).compareTo("USER:") == 0) {
                    temp = temp.substring(5, temp.length());
                    int index = temp.indexOf(':');
                    String email = temp.substring(0, index);
                    String pass = temp.substring(index + 1, temp.length());
                    System.out.println(email + " " + pass);
                    // I have added just these two lines of code let's see how it works. 
                    LiveSeminar.stext[LiveSeminar.live.thirdcount] = new Struct("", email, "", sock);
                    LiveSeminar.live.thirdcount++;
                    Connection conn = MySqlConnect.connection();
                    String q = "select * from login where email='" + Login.email + "';";
                    pst = conn.prepareStatement(q);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String password = rs.getString("password");
                        if (password.compareTo(pass) == 0) {
                            p.print("YES\n");
                            p.flush();
                            System.out.println("YES\n");
                            LiveSeminar.live.setData("root: " + email);
                        } else {
                            p.print("NO\n");
                            p.flush();
                            System.out.println("NO\n");
                            LiveSeminar.live.setData("user: " + email);
                        }
                    } else {
                        p.print("NO\n");
                        p.flush();
                        System.out.println("NO\n");
                        LiveSeminar.live.setData("user: " + email);
                    }
                } else if (temp.substring(0, 5).compareTo("SEND:") == 0) {
                    temp = temp.substring(5, temp.length());
                    int index = temp.indexOf(':');
                    String email = temp.substring(0, index);
                    temp = temp.substring(index + 1, temp.length());
                    index = temp.indexOf('#');
                    String IP = temp.substring(0, index);
                    String ques = "Question. " + temp.substring(index + 1, temp.length());
                    LiveSeminar.sbutton[LiveSeminar.live.count] = new Struct(ques, email, IP, sock);
                    DefaultListModel model = new DefaultListModel();
                    index = LiveSeminar.live.count;
                    for (int i = 0; i < index; i++) {
                        model.addElement(LiveSeminar.sbutton[i].ques);
                    }
                    model.addElement(ques);
                    LiveSeminar.list.setModel(model);
                    LiveSeminar.live.count++;
                } else if (temp.substring(0, 5).compareTo("LECT:") == 0) {
                    temp = temp.substring(5, temp.length());
                    for (int i = 0; i <= LiveSeminar.live.papacount; i++) {
                        Socket sock1 = LiveSeminar.socket[i];
                        if (!sock.equals(sock1)) {
                            p = new PrintStream(sock1.getOutputStream());
                            p.println("TEXT:" + temp);
                            p.flush();
                        }
                    }
                } else if (temp.compareTo("EXIT:") == 0 || temp.compareTo("EXIT") == 0) {
                    Socket current = sock;
                    int index = 0;
                    for (int i = 0; i <= LiveSeminar.live.papacount; i++) {
                        if (LiveSeminar.socket[i] == current) {
                            index = i;
                            break;
                        }
                    }
                    for (int i = index + 1; i <= LiveSeminar.live.papacount; i++)
                        LiveSeminar.socket[i - 1] = LiveSeminar.socket[i];
                    LiveSeminar.live.papacount--;

                    index = 0;
                    for (int i = 0; i < LiveSeminar.live.thirdcount; i++) {
                        if (LiveSeminar.stext[i].sock == current) {
                            index = i;
                            break;
                        }
                    }
                    for (int i = index + 1; i < LiveSeminar.live.thirdcount; i++) {
                        LiveSeminar.stext[i - 1] = LiveSeminar.stext[i];
                    }
                    LiveSeminar.live.thirdcount--;
                    LiveSeminar.live.letsClear();
                    for (int i = 0; i < LiveSeminar.live.thirdcount; i++) {
                        LiveSeminar.live.setData("    " + LiveSeminar.stext[i].email);
                    }
                    sock.close();
                    System.out.println("One user exited.");
                    break;
                    //Try resolving other other stuffs.
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("Error Occurred\n");
        }
    }
}
