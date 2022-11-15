import java.util.*;
import java.net.*;
import java.io.*;

public class Server
{
    public static void main(String[] args) {
    	String upper;
        DatagramSocket sock = null;
        int port;
        Scanner sc = new Scanner(System.in);
        
    	try {
            System.out.println("Enter Port: ");
            port = sc.nextInt();
            sock = new DatagramSocket(port);
            System.out.println("Listening for connections on port " + port +" ...");
        
            while (true) {
                byte[] buffer = new byte[1000];
            	DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                sock.receive(request);
            	// read client input and change to uppercase
                upper = new String(request.getData());
                System.out.println("Message Recieved: " + upper);
                upper = upper.toUpperCase();
                System.out.println("Message Sent: " + upper);
                byte [] b = upper.getBytes();
                DatagramPacket reply = new DatagramPacket(b, b.length, request.getAddress(), request.getPort());
                sock.send(reply);
            }
            
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
     {if(sock != null) sock.close();}
    }
} 
    
