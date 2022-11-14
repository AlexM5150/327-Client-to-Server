import java.util.*;
import java.net.*;
import java.io.*;

public class Server
{
    public static void main(String[] args) {
    	String upper;
        DatagramSocket sock = null;
        int port;
        boolean f = true;
        Scanner sc = new Scanner(System.in);
        
    	try {
            System.out.println("Enter Port: ");
            port = sc.nextInt();
            sock = new DatagramSocket(port);
            System.out.printf("Listening for connections on port ", port," ...");
            byte[] buffer = new byte[1000];
            
            while (f) {
            	DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                sock.receive(request);
            	// read client input and change to uppercase
                upper = request.getData().toString().toUpperCase();
                byte [] b = upper.getBytes();
                DatagramPacket reply = new DatagramPacket(b, b.length, request.getAddress(), request.getPort());
                sock.send(reply);
                sc.close();
            }
            sock.close();
            
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
     //{if(sock != null) sock.close();}
    }
} 
    
