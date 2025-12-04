package ait.socket.client.task;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SendHandler implements Runnable {
    private final Socket socket;

    public SendHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            Scanner consoleScaner = new Scanner(System.in);
            System.out.println("Enter your message or 'exit' to quit ");
            String message = consoleScaner.nextLine();
            while (!"exit".equalsIgnoreCase(message)) {
                socketWriter.println(socket.getLocalPort() + " " + message + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                socketWriter.flush();
                System.out.println("Enter your message or 'exit' to quit ");
                message = consoleScaner.nextLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
