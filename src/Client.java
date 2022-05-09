import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) throws IOException {
        String artistName, serverMessage;

        /* Open a connection to the server, create the client socket */
        Socket clientSocket = new Socket(Credentials.HOST, Credentials.PORT);
        System.out.println("Client is running");

        /* Create I/O streams to read/write data, PrintWriter and BufferedReader */
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        /* Read messages continuously until the user types "stop" */
        while (true) {
            // Get artist name from client
            System.out.println("Enter the artist name:");
            artistName = inFromUser.readLine();
            // Send message to server
            outToServer.println(artistName);
            // Receive response from the server
            serverMessage = inFromServer.readLine();
            System.out.println("You entered " + artistName);
            System.out.println("FROM SERVER: " + serverMessage);
            // when "stop" is entered
            if (artistName.equals("stop")) {
                break;
            }
        }
        // Close I/O streams and socket
        inFromServer.close();
        inFromUser.close();
        outToServer.close();
        clientSocket.close();
    }
}
