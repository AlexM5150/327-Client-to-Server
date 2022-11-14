import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static void main(String[] args) {
        String strIp, strMessage;
        int port;
        DatagramSocket sock = null;
        boolean f = true;
        Scanner sc = new Scanner(System.in);

        try {
            // Run while the client is active
            while (f) {
                //make connection to socket
                System.out.println("Enter IP address: ");
                strIp = sc.nextLine();
                InetAddress ip = InetAddress.getByName(strIp);

                System.out.println("Enter Port: ");
                port = sc.nextInt();
                sock = new DatagramSocket(port, ip);

                System.out.println("Enter Message: ");
                strMessage = sc.nextLine();
                byte [] message = strMessage.getBytes();

                DatagramPacket request = new DatagramPacket(message, message.length, ip, port);
                sock.send(request);
                
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));
                sock.close();
                sc.close();
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
     //{if(sock != null) sock.close();}
    }
} 