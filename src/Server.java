import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Wakuma Bayan
 * server class creates an instace of ServerSocket to commuincate with client
 */
//test comment

public class Server extends Calculator {

    public static void main(String[] args) throws IOException { // main
        // create fields
        ServerSocket ss = null;
        Socket clientSocket = null;
        PrintWriter writer = null; // write to client
        BufferedReader reader = null; // read from client

        // get local machine Ip address
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("Server IP address: " + address.getHostAddress()); // print ip

        try {
            int port = 8088; //create port
            System.out.println("------------------------------------------------");
            System.out.println("Binding to port " + port + ", please wait...");
            ss = new ServerSocket(port);
            System.out.println("server started " + ss);
            // start listening for incoming client requests
            System.out.println("server is listening for connections on port " + port);

            clientSocket = ss.accept();// bind the connection
            System.out.println("Client accepted: " + clientSocket);
            System.out.println("------------------------------------------------\n");

            // write to a client
            writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            // read strings from clients
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // while true continue to communicate with client
            int answer = 0;
            while (true) {
                String read = reader.readLine();

                //  check to see if client greeted the server
                if (read.contains("hello")) {
                    writer.write("Hello client !!!\n");// respond
                } else if (read.equalsIgnoreCase("how are you")) {
                    writer.write("Im good how about you\n");// respond
                } else if (read.contains("+") || read.contains("-") || read.contains("*") || read.contains("/") || read.contains("%")) {
                    String[] parts = read.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)"); // split on operators, including cases without spaces
                    answer = compute(parts[0] + " " + parts[1] + " " + parts[2]); // concatenate with spaces and compute
                    writer.write("answer is: " + answer + "\n");
                } else if (read.equalsIgnoreCase("bye")) {// check to see if client has quit
                    System.out.println("""
                            -----------------------------------
                            the client has quite
                            Shutting the server down""");
                    writer.write("See you next time!!");
                    writer.flush();
                    ss.close();
                    break;
                } else {
                    writer.write("that feature is not available, please try something else");
                }
                System.out.println("client: " +read);

                writer.println();// new line character
                writer.flush();// flush internal buffer;

            }
            // close resources
            //ss.close();
            reader.close();
            writer.close();

        } catch (Exception e) {
            System.out.println("error occurred: Client lost\n" + e.getMessage() + "\n");
            ss.close();// close instance of Server Socket
        }
    }
}

