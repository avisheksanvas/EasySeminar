package seminarserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{

    
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        int i = 0 ;
        
        try
        {
            //System.out.println(i);
            LiveSeminar.live = new LiveSeminar();
            //LiveSeminar.live.setVisible(true);
            new Login().setVisible(true);
            ServerSocket s1 = new ServerSocket(1342);
            while( true )
            {
                //LiveSeminar.live.papacount=i;
                Socket ss = s1.accept();
                LiveSeminar.live.papacount=i;
                LiveSeminar.socket[i]=ss;
                ListenThread t1 = new ListenThread(ss,i);
                t1.start();
                i++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.out.println("Exception in Creating Thread\n");
        }
            
    }
}