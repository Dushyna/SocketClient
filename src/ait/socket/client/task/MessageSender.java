package ait.socket.client.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private final Socket socket;  // Для организации обмена данными с сервером

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket) {
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true); // Создание выходного потока
            Scanner consoleScaner = new Scanner(System.in);
            System.out.println("Enter your Name ");
            String name = consoleScaner.nextLine();
            System.out.println("Enter your message or 'exit' to quit ");
            String message = consoleScaner.nextLine();
            while (!"exit".equalsIgnoreCase(message)) {
                socketWriter.printf("%s [%s] %s \n", name, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), message);
                message = consoleScaner.nextLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
