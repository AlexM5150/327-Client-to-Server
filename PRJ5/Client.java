import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static void main(String[] args) {
        //define initial variables
        String strIp, strMessage;
        int port;
        DatagramSocket sock = null;
        Scanner sc = new Scanner(System.in);
        Console console = System.console();

        try {
            //get ip and port via user input
            System.out.println("Enter IP address: ");
            strIp = sc.nextLine();
            //convert string ip into InetAddress object
            InetAddress ip = InetAddress.getByName(strIp);
            System.out.println("Enter Port: ");
            port = sc.nextInt();
            //infinite loop to send unlimited messages
            while (true) {
                //create new datagram socket and set 15 sec timeout so a socket isn't left hanging with no reply
                sock = new DatagramSocket();
                sock.setSoTimeout(15000);
                //console application to read the message from user input
                strMessage = console.readLine("\nMessage: ");
                //convert string message into bytes
                byte [] message = strMessage.getBytes();
                //put message into a packet with ip/port to send to server and packet is sent
                DatagramPacket request = new DatagramPacket(message, message.length, ip, port);
                sock.send(request);
                //byte size is set for reply from server and reply packet is recieved
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));
            }
        //exceptions to catch socket execptions and user input errors    
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (SocketTimeoutException s){System.out.println("Socket timed out, check the port");
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());}
     {if(sock != null) sock.close();}
    }
} 