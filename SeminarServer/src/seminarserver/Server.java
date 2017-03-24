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
            LiveSeminar.live.setVisible(true);
            ServerSocket s1 = new ServerSocket(1342);
        
            while( true )
            {
            i++;
            Socket ss = s1.accept();
            ListenThread t1 = new ListenThread(ss,i);
            t1.start();
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.out.println("Exception in Creating Thread\n");
        }
            
    }
}