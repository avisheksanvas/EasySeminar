package seminarserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhey
 */
public class Struct {
    public String ques;
    public String email;
    public String IP;
    public java.net.Socket sock;
    
    public Struct(String ques,String email,String IP,java.net.Socket sock){
        this.ques=ques;
        this.email=email;
        this.IP=IP;
        this.sock=sock;
    }   
}
