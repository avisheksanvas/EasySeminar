
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminarserver;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author abhey
 */

/*
There is a bug when both two files have same name but different loctations.
*/
//It is the time to test it baby....
public class HomePage extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        this.jList2.addMouseListener(this);
        this.jList1.addMouseListener(this);
        this.getContentPane().setBackground(new java.awt.Color(182, 222, 223));
        Mail.getContentPane().setBackground(new java.awt.Color(182, 222, 223));
        Open.getContentPane().setBackground(new java.awt.Color(182, 222, 223));
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = MySqlConnect.connection();
        try{
            String q = "SELECT * FROM Notes where email='"+Login.email+"';";
            DefaultListModel model = new DefaultListModel();
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            while(rs.next()){
                model.addElement("  "+rs.getString("filename"));
            }
            jList2.setModel(model);
            q = "SELECT * FROM unanswered WHERE userid='"+Login.userid+"';";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            model = new DefaultListModel();
            while(rs.next()){
                model.addElement("  "+rs.getString("question").substring(10));
            }
            jList1.setModel(model);
        }
        catch(Exception e){
            System.out.println(e+"Error Occurred");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Open = new javax.swing.JFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        Mail = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        T1 = new javax.swing.JTextField();
        P1 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        T2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        T3 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        Open.setTitle("Add File");
        Open.setLocation(new java.awt.Point(500, 200));
        Open.setMinimumSize(new java.awt.Dimension(460, 420));
        Open.setResizable(false);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OpenLayout = new javax.swing.GroupLayout(Open.getContentPane());
        Open.getContentPane().setLayout(OpenLayout);
        OpenLayout.setHorizontalGroup(
            OpenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OpenLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        OpenLayout.setVerticalGroup(
            OpenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OpenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Mail.setTitle("Send Mail");
        Mail.setLocation(new java.awt.Point(450, 170));
        Mail.setMaximumSize(new java.awt.Dimension(495, 530));
        Mail.setMinimumSize(new java.awt.Dimension(495, 530));
        Mail.setResizable(false);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setText("Compose Mail");

        jLabel6.setText("Password");

        jLabel7.setText("Senders Mail ID");

        T1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Message");

        T2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Subject");

        T3.setColumns(20);
        T3.setRows(5);
        jScrollPane3.setViewportView(T3);

        jButton4.setText("Send Mail");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MailLayout = new javax.swing.GroupLayout(Mail.getContentPane());
        Mail.getContentPane().setLayout(MailLayout);
        MailLayout.setHorizontalGroup(
            MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MailLayout.createSequentialGroup()
                .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MailLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel4))
                    .addGroup(MailLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MailLayout.createSequentialGroup()
                                .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(T1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(P1))
                                .addGap(80, 80, 80))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(T2))))
                .addGap(20, 20, 20))
            .addGroup(MailLayout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MailLayout.setVerticalGroup(
            MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(MailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MailLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MailLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Page");
        setLocation(new java.awt.Point(425, 175));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setText("Your Notes");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel2.setText("Unanswered Questions");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setText("Welcome To Home");

        jList1.setToolTipText("Double click on any item to answer question via mail.");
        jScrollPane1.setViewportView(jList1);

        jList2.setToolTipText("Double click on any item to open it");
        jScrollPane2.setViewportView(jList2);

        jButton1.setText("Delete Note");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add Note");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete Question");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(71, 71, 71)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addGap(39, 39, 39))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        if(evt.getActionCommand().compareTo("CancelSelection")==0){
            Open.setVisible(false);
        }
        else{
            File file = jFileChooser1.getSelectedFile();
            String filename = file.getName();
            String filepath = file.getAbsolutePath();
            String emial = Login.email;
            Connection conn = MySqlConnect.connection();
            String q = "SELECT * FROM Notes;";
            PreparedStatement pst = null;
            ResultSet rs = null;
            try{
                pst = conn.prepareStatement(q);
                rs = pst.executeQuery();
                int n=0;
                while(rs.next()){
                    n++;
                }
                q = "INSERT INTO Notes VALUES('"+n+"','"+Login.email+"','"+filename+"','"+filepath+"');";
                pst = conn.prepareStatement(q);
                pst.executeUpdate();
                q = "SELECT * FROM Notes WHERE email='"+Login.email+"';";
                DefaultListModel model = new DefaultListModel();
                pst = conn.prepareStatement(q);
                rs = pst.executeQuery();
                while(rs.next()){
                    model.addElement("  "+rs.getString("filename"));
                }
                jList2.setModel(model);
                Open.setVisible(false);
            }
            catch(Exception e){
                System.out.println(e+"Error Occurred");
            }
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int index = jList2.getSelectedIndex();
        if(index!=-1){
            Connection conn = MySqlConnect.connection();
            String q = "DELETE FROM Notes WHERE filename='"+jList2.getSelectedValue().substring(2)+"'and email='"+Login.email+"';";
            PreparedStatement pst = null;
            ResultSet rs = null;
            try{
                pst = conn.prepareStatement(q);
                pst.executeUpdate();
                q = "SELECT * FROM Notes WHERE email='"+Login.email+"';";
                DefaultListModel model = new DefaultListModel();
                pst = conn.prepareStatement(q);
                rs = pst.executeQuery();
                while(rs.next()){
                    model.addElement("  "+rs.getString("filename"));
                }
                jList2.setModel(model);
            }
            catch(Exception e){
                System.out.println(e+"Exception Occurred");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Select the note which you want to remove");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Open.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //Remember this is the place you need to change when you change format of unanwered list....
        String question = "Question. "+jList1.getSelectedValue().substring(2);
        Connection conn = MySqlConnect.connection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String q = "DELETE FROM unanswered WHERE userid='"+Login.userid+"' and question='"+question+"';";
        try{
            pst = conn.prepareStatement(q);
            pst.executeUpdate();
            q = "SELECT * FROM unanswered WHERE userid='"+Login.userid+"';";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            DefaultListModel model = new DefaultListModel();
            while(rs.next()){
                model.addElement("  "+rs.getString("question").substring(10));
            }
            jList1.setModel(model);
        }
        catch(Exception e){
            System.out.println(e+"Error Occurred");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void T1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T1ActionPerformed

    private void T2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String from = T1.getText();
        String pass = P1.getText();
        String subject = T2.getText();
        String message = T3.getText();
        Connection conn = MySqlConnect.connection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String question = "Question. "+jList1.getSelectedValue().substring(2);
        String q = "SELECT * FROM unanswered where question='"+question+"'and userid='"+Login.userid+"';";
        try{
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            while(rs.next()){
                String to = rs.getString("email");
                Mailer mail = new Mailer();
                mail.send(from, pass, to, subject, message);
            }
            q = "DELETE FROM unanswered where question='"+question+"'and userid='"+Login.userid+"';";
            pst = conn.prepareStatement(q);
            pst.executeUpdate();
            q = "SELECT * FROM unanswered WHERE userid='"+Login.userid+"';";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            DefaultListModel model = new DefaultListModel();
            while(rs.next()){
                model.addElement("  "+rs.getString("question").substring(10));
            }
            jList1.setModel(model);
            Mail.setVisible(false);
        }
        catch(Exception e){
            System.out.println(e+"Error Occurred");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Mail;
    private javax.swing.JFrame Open;
    private javax.swing.JPasswordField P1;
    private javax.swing.JTextField T1;
    private javax.swing.JTextField T2;
    private javax.swing.JTextArea T3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getClickCount()==2&&e.getComponent().equals(jList2)){
           int index = jList2.getSelectedIndex();
           String name = jList2.getSelectedValue().substring(2);
           String q = "SELECT * FROM Notes WHERE filename='"+name+"';";
           Connection conn = MySqlConnect.connection();
           ResultSet rs = null;
           PreparedStatement pst = null;
           try{
               pst = conn.prepareStatement(q);
               rs = pst.executeQuery();
               File file = null;
               if(rs.next()){
                   file = new File(rs.getString("filepath"));
                   Desktop.getDesktop().open(file);
               }
               else{
                   JOptionPane.showMessageDialog(null,"Error while opening file");
               }
           }
           catch(Exception ex){
               System.out.println(ex+"Exception Occurred");
           }
       }
       if(e.getClickCount()==2&&e.getComponent().equals(jList1)){
           JOptionPane.showMessageDialog(null,"Please make sure that the mail client you use allows use of less secure apps.");
           Mail.setVisible(true);
       }
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
