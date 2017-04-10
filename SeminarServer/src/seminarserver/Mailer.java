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
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
public class Mailer{  
    private static String email;
    private static String password;
    public void send(String from,String password,String to,String sub,String message){  
        email = from;
        Mailer.password = password;
          Properties props = new Properties();
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(message);
            msg.setSubject(sub);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        } 
             
    }
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(email, password);
        }
    }
}  
