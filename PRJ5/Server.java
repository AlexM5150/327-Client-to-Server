import java.util.*;
import java.net.*;
import java.io.*;

public class Server
{
    public static void main(String[] args) {
        //define initial variables
    	String upper;
        DatagramSocket sock = null;
        int port;
        Scanner sc = new Scanner(System.in);
        
    	try {
            //get port from user input
            System.out.println("Enter Port: ");
            port = sc.nextInt();
            //create new socket on that port
            sock = new DatagramSocket(port);
            System.out.println("Listening for connections on port " + port +" ...");
            //infinite loop so unlimited messages can be recieved and replies sent
            while (true) {
                //byte size set and is reset each iteration 
                byte[] buffer = new byte[1000];
            	DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                sock.receive(request);
            	// read client input and change to uppercase
                upper = new String(request.getData());
                System.out.println("Message Recieved: " + upper);
                upper = upper.toUpperCase();
                System.out.println("Message Sent: " + upper);
                byte [] b = upper.getBytes();
                //reply packet is created with new uppercase message and sent to client
                DatagramPacket reply = new DatagramPacket(b, b.length, request.getAddress(), request.getPort());
                sock.send(reply);
            }
        //exceptions to catch socket execptions and user input errors                
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
     {if(sock != null) sock.close();}
    }
} 
    
