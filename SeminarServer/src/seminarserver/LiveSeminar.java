/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminarserver;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.sql.*;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 *
 * @author abhey
 */
public class LiveSeminar extends javax.swing.JFrame implements MouseListener {

    public static LiveSeminar live;
    public static Struct sbutton[] = new Struct[256];
    public static javax.swing.JScrollPane scroll;
    public static javax.swing.JList < String > list;
    /*It's all side effect of hackathon we don't need these much variables but still i will try my best to resolve all
    this stuff */
    public int count = 0;
    public int papacount = 0;
    public int thirdcount = 0;
    public static String password;
    public static String user;
    public static Socket socket[] = new Socket[256];
    public static Struct stext[] = new Struct[256];
    /**
     * Creates new form LiveSeminar
     */
    public LiveSeminar() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(new java.awt.Color(182, 222, 223));
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                Connection conn = MySqlConnect.connection();
                try {
                    PreparedStatement pst = conn.prepareStatement("Select * from unanswered");
                    ResultSet rs = pst.executeQuery();
                    int n = 0;
                    while (rs.next()) {
                        n++;
                    }
                    int index = list.getLastVisibleIndex();
                    String str;
                    for (int i = 0; i <= index; i++) {
                        str = "Insert into unanswered values('" + (n + index) + "','" + Login.userid + "','" + LiveSeminar.sbutton[index].ques + "','" + LiveSeminar.sbutton[index].email + "')";
                        conn.createStatement().execute(str);
                    }
                    System.out.print("Everything closed manully by me");
                    System.exit(0);
                } catch (Exception ex) {
                    System.out.println(ex + "Error Occurred");
                }
            }
        });
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        list = this.jList1;
        Question.getContentPane().setBackground(new java.awt.Color(182, 222, 223));
        this.jList1.addMouseListener(this);
        live = this;
        Runtime run = Runtime.getRuntime();
        try {
            user = JOptionPane.showInputDialog("Enter user name");
            if (user != null) {
                password = JOptionPane.showInputDialog("Enter root password");
                String command = "echo " + password + " | sudo -S /etc/init.d/vsftpd restart";
                String[] cmd = {
                    "/bin/bash",
                    "-c",
                    command
                };
                Process proc = run.exec(cmd, null, new java.io.File("/home/" + user));
                Scanner scan = new Scanner(proc.getInputStream());
                proc.waitFor();
                if (!scan.hasNext()) {
                    JOptionPane.showMessageDialog(null, "Sorry you entered wrong username or password.");
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You must specify a user name");
                System.exit(0);
            }
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "Please check that you have entered right username and password and that your PC conforms to requirements.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e + "Error Occurred");
            System.exit(0);
        }

    }
    public void setData(String str) {
        this.TextArea.append(str + "\n");
    }

    public void letsClear() {
        this.TextArea.setText("");
    }

    /*
    public void addListener(int index){
        LiveSeminar.sbutton[index].button.addMouseListener(this);
    }
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Question = new javax.swing.JFrame();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        Open = new javax.swing.JFrame();
        OpenFile = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        Question.setLocation(new java.awt.Point(450, 300));
        Question.setMinimumSize(new java.awt.Dimension(550, 95));

        jButton3.setText("Answer To All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Private Answer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Answer later");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Reject Question");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QuestionLayout = new javax.swing.GroupLayout(Question.getContentPane());
        Question.getContentPane().setLayout(QuestionLayout);
        QuestionLayout.setHorizontalGroup(
            QuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(51, 51, 51)
                .addComponent(jButton4)
                .addGap(28, 28, 28)
                .addComponent(jButton5)
                .addContainerGap())
        );
        QuestionLayout.setVerticalGroup(
            QuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestionLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(QuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Open.setLocation(new java.awt.Point(500, 200));
        Open.setMinimumSize(new java.awt.Dimension(460, 420));
        Open.setResizable(false);

        OpenFile.setName(""); // NOI18N
        OpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OpenLayout = new javax.swing.GroupLayout(Open.getContentPane());
        Open.getContentPane().setLayout(OpenLayout);
        OpenLayout.setHorizontalGroup(
            OpenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OpenLayout.setVerticalGroup(
            OpenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OpenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Live Seminar");
        setLocation(new java.awt.Point(350, 100));
        setMaximumSize(new java.awt.Dimension(675, 490));
        setMinimumSize(new java.awt.Dimension(675, 490));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setText("Live Seminar Server");

        TextArea.setEditable(false);
        TextArea.setColumns(20);
        TextArea.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        TextArea.setRows(5);
        jScrollPane1.setViewportView(TextArea);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel2.setText("Question Being Asked");

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButton1.setText("Transfer Files");
        jButton1.setToolTipText("Please make sure that the are files you  want to send are in users home directory.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close This Connection");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jButton7.setText("Make Announcements");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Get IP");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setText("Email ID Of Connected Users");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(69, 69, 69)
                        .addComponent(jButton7)
                        .addGap(48, 48, 48)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(117, 117, 117)
                                .addComponent(jLabel2)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton7)
                    .addComponent(jButton2)
                    .addComponent(jButton8))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            // TODO add your handling code here
            int index = list.getSelectedIndex();
            String question = sbutton[index].ques;
            String answer = JOptionPane.showInputDialog("Type your answer here");
            BufferedReader br = null;
            PrintWriter p = null;
            if (answer == null)
                return;
            else {
                try {
                    Socket sock = null;
                    for (int i = 0; i <= LiveSeminar.live.papacount; i++) {
                        sock = LiveSeminar.socket[i];
                        if (!sock.isClosed()) {
                            p = new PrintWriter(sock.getOutputStream());
                            /*
                            p.println("QUER:2" + question);
                            p.println("QUER:3Ans. " + answer);
                            */
                            p.println("QUER:2"+question+"&^%"+answer);
                        }
                    }
                    for (int i = index + 1; i < count; i++) {
                        LiveSeminar.sbutton[i - 1] = LiveSeminar.sbutton[i];
                    }
                    count--;
                    DefaultListModel model = new DefaultListModel();
                    for (int i = 0; i < count; i++) {
                        model.addElement(LiveSeminar.sbutton[i].ques);
                    }
                    LiveSeminar.list.setModel(model);
                    Question.setVisible(false);
                } catch (Exception e) {
                    System.out.println(e + "Exception occurred while answering all");
                }
            }
        }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            // TODO add your handling code here:
            int index = list.getSelectedIndex();
            for (int i = index + 1; i < count; i++) {
                LiveSeminar.sbutton[i - 1] = LiveSeminar.sbutton[i];
            }
            count--;
            DefaultListModel model = new DefaultListModel();
            for (int i = 0; i < count; i++) {
                model.addElement(LiveSeminar.sbutton[i].ques);
            }
            LiveSeminar.list.setModel(model);
            Question.setVisible(false);
        }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            // TODO add your handling code here:
            Connection conn = MySqlConnect.connection();
            try {
                int index = list.getSelectedIndex();
                PreparedStatement pst = conn.prepareStatement("Select * from unanswered");
                ResultSet rs = pst.executeQuery();
                int n = 0;
                while (rs.next()) {
                    n++;
                }
                String sql = "Insert into unanswered values('" + n + "','" + Login.userid + "','" + LiveSeminar.sbutton[index].ques + "','" + LiveSeminar.sbutton[index].email + "')";
                conn.createStatement().execute(sql);
                for (int i = index + 1; i < count; i++) {
                    LiveSeminar.sbutton[i - 1] = LiveSeminar.sbutton[i];
                }
                count--;
                DefaultListModel model = new DefaultListModel();
                for (int i = 0; i < count; i++) {
                    model.addElement(LiveSeminar.sbutton[i].ques);
                }
                LiveSeminar.list.setModel(model);
                Question.setVisible(false);
            } catch (Exception e) {
                System.out.println("Error Occurred");
            }
        }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            // TODO add your handling code here:
            int index = list.getSelectedIndex();
            Socket sock = null;
            boolean open = false;
            for (int i = 0; i < LiveSeminar.live.thirdcount; i++) {
                if (LiveSeminar.sbutton[index].email.compareTo(LiveSeminar.stext[i].email) == 0) {
                    if (!LiveSeminar.stext[i].sock.isClosed()) {
                        sock = LiveSeminar.stext[i].sock;
                        open = true;
                        break;
                    }
                }
            }
            if (!open) {
                JOptionPane.showMessageDialog(null, "Sorry you can't answer this question privately as user has left.\nWe have added this question in your unanswered section you\ncan reply to the user via an email.");
            } else {
                BufferedReader br = null;
                PrintStream p = null;
                String temp;
                try {
                    //br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    p = new PrintStream(sock.getOutputStream());
                    String output = JOptionPane.showInputDialog("Type your answer here");
                    if (output == null)
                        return;
                    else {
                        //A change is intoduced here.
                        /*
                        p.println("QUER:2" + sbutton[index].ques);
                        p.println("QUER:3Ans. " + output);
                        */
                        p.println("QUER:2"+sbutton[index].ques+"&^%"+output);
                    }
                    for (int i = index + 1; i < count; i++) {
                        LiveSeminar.sbutton[i - 1] = LiveSeminar.sbutton[i];
                    }
                    count--;
                    DefaultListModel model = new DefaultListModel();
                    for (int i = 0; i < count; i++) {
                        model.addElement(LiveSeminar.sbutton[i].ques);
                    }
                    LiveSeminar.list.setModel(model);
                    Question.setVisible(false);
                } catch (Exception e) {
                    System.out.println(e + "Error Occurred while replying privately.");
                }
            }
        }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            // TODO add your handling code here:
            Open.setVisible(true);
        }//GEN-LAST:event_jButton1ActionPerformed

    private static InetAddress getFirstNonLoopbackAddress(boolean preferIpv4, boolean preferIPv6) throws SocketException {
        Enumeration en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface i = (NetworkInterface) en.nextElement();
            for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements();) {
                InetAddress addr = (InetAddress) en2.nextElement();
                if (!addr.isLoopbackAddress()) {
                    if (addr instanceof Inet4Address) {
                        if (preferIPv6) {
                            continue;
                        }
                        return addr;
                    }
                    if (addr instanceof Inet6Address) {
                        if (preferIpv4) {
                            continue;
                        }
                        return addr;
                    }
                }
            }
        }
        return null;
    }


    private void OpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenFileActionPerformed
            // TODO add your handling code here:
            if (evt.getActionCommand().compareTo("CancelSelection") == 0) {
                Open.setVisible(false);
            } else {
                File file = OpenFile.getSelectedFile();
                String Path = file.getAbsolutePath();
                String base = "/home/" + user;
                String relative = new File(base).toURI().relativize(new File(Path).toURI()).getPath();
                try {
                    InetAddress inet = LiveSeminar.getFirstNonLoopbackAddress(true, false);
                    String temp = inet.toString().substring(1);
                    String fttpPath = "FILE:ftp://" + user + ":" + password + "@" + temp + "/" + relative;
                    BufferedReader br = null;
                    PrintStream p = null;
                    for (int i = 0; i <= papacount; i++) {
                        //System.out.println(LiveSeminar.sbutton[i].email);
                        Socket sock = LiveSeminar.socket[i];
                        br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        p = new PrintStream(sock.getOutputStream());
                        p.println(fttpPath);
                        System.out.println(fttpPath + "Sccuess");
                    }
                } catch (Exception e) {
                    System.out.println(e + "Error Occurred");
                }
                Open.setVisible(false);
            }
        }//GEN-LAST:event_OpenFileActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
            // TODO add your handling code here:
            String str = JOptionPane.showInputDialog("Enter your announcement here");
            BufferedReader br = null;
            PrintStream p = null;
            //System.out.println(papacount);
            try {
                Socket sock;
                for (int i = 0; i <= papacount; i++) {
                    sock = LiveSeminar.socket[i];
                    //br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    p = new PrintStream(sock.getOutputStream());
                    p.println("ANNO:" + str);
                    p.flush();
                    System.out.println(str + "SUCCESS");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e + "Error Occurred Here");
            }
        }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            // TODO add your handling code here:
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            try {
                // TODO add your handling code here:
                InetAddress addr = LiveSeminar.getFirstNonLoopbackAddress(true, false);
                String IP = addr.toString().substring(1);
                JOptionPane.showMessageDialog(null, "IP Address is: " + IP + "\nPort is: 1342");
            } catch (SocketException ex) {
                Logger.getLogger(LiveSeminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LiveSeminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LiveSeminar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Open;
    private javax.swing.JFileChooser OpenFile;
    private javax.swing.JFrame Question;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && this.jList1.getSelectedIndex() >= 0) {
            Question.setVisible(true);
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
