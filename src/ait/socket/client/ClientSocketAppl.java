package ait.socket.client;
import ait.socket.client.task.RecieveHandler;
import ait.socket.client.task.SendHandler;
import java.io.*;
import java.net.Socket;

public class ClientSocketAppl {
    public static void main(String[] args) throws InterruptedException {
        String serverIp = "127.0.0.1"; //localhost
        int serverPort = 9000;

        try (Socket socket = new Socket(serverIp, serverPort)) {
            Thread sendThread = new Thread(new SendHandler(socket));
            Thread receiveThread = new Thread(new RecieveHandler(socket));
            receiveThread.setDaemon(true);
            sendThread.start();
            receiveThread.start();
            sendThread.join();
        } catch (IOException e) {
            System.err.println("Error connecting to server or I/O error: " + e.getMessage());
        } finally {
            System.out.println("Client application finished.");
        }
    }

}

