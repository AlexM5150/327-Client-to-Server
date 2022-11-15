import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static void main(String[] args) {
        String strIp, strMessage;
        int port;
        DatagramSocket sock = null;
        Scanner sc = new Scanner(System.in);

        try {
            Console console = System.console();
            System.out.println("Enter IP address: ");
            strIp = sc.nextLine();
            InetAddress ip = InetAddress.getByName(strIp);

            System.out.println("Enter Port: ");
            port = sc.nextInt();
            while (true) {
                
                sock = new DatagramSocket();
                //sock.setSoTimeout(5000);

                //System.out.println("Enter Message: ");
                strMessage = console.readLine("\nMessage: ");//sc.nextLine();
                byte [] message = strMessage.getBytes();

                DatagramPacket request = new DatagramPacket(message, message.length, ip, port);
                sock.send(request);
                
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (SocketTimeoutException s){System.out.println("Socket timed out, check the port");
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
     {if(sock != null) sock.close();}
    }
} 