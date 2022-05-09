import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {
        /* Open the server socket */
        ServerSocket serverSocket = new ServerSocket(Credentials.PORT);
        System.out.println("Server is running");

        /* Create a Database object and check the connection with establishDBConnection(): */
        Database db = new Database();

        /* If the db connection fails, print: */
        if (!db.establishDBConnection()) {
            System.out.println("DB connection fail, stopping.");
            serverSocket.close();
        } else { /* else connect to DB and print: */
            System.out.println("Server is now connected to DB");
            int clientId = 0;
            /* Continuously listen for client requests */
            while (true) {
                /* Accept new connection and create the client socket */
                Socket clientSocket = serverSocket.accept();
                /* Increment clientId. The clientId is not reassigned once used. */
                clientId++;
                /* Display clientId and IP address: */
                System.out.println("Client " + clientId + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
                /* Create a new client handler object and start the thread */
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientId, db);
                new Thread(clientHandler).start();
            }
        }
    }
}

