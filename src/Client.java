import java.io.*;
import java.net.Socket;
import java.util.Scanner;


/**
 * socket class for communicating with a server
 */
public class Client extends PublicIp{

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("enter an Ip Address: "); // get the ip address
        String address = input.nextLine();

        Socket clientSocket = null;
        PrintWriter clientWriter = null;
        BufferedReader in = null;
        //InetAddress address = InetAddress.getLocalHost();
        System.out.println("Server IP address: " + address);

        try {
            System.out.println("Establishing connection. Please wait");
            clientSocket = new Socket(address, 8088);
            System.out.println("connection established " + clientSocket);
            System.out.print("----------------------------------------------\n");
            // write to server
            clientWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            // read server response
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scan = new Scanner(System.in);

            // while loop to communicate with server
            while(true) {

                // send a message to server
                System.out.print("communicate with server: ");
                String message = scan.nextLine();
                // write to server from the command line

                clientWriter.write(message);
                clientWriter.println(); // create a new line character
                clientWriter.flush();// flush internal buffer

                // read from server
                String serverMessage = in.readLine();
                System.out.println("Server said: " + serverMessage);

                // disconnect client
                if(serverMessage.equalsIgnoreCase("See you next time!!")) {
                    System.out.println("Connection disconnected at port " + clientSocket );
                    break;// break the loop
                }
            }
            // close resources
        }catch(IOException e){
            System.out.println("server not found: " + e.getMessage());
        } finally{
            try {
                if (clientSocket != null)
                    clientSocket.close();
                if (clientWriter != null)
                    clientWriter.close();
                if (in != null)
                    in.close();

            } catch(IOException e){

            }

        }
    }

}
