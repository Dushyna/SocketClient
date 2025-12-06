package ait.socket.client;

import ait.socket.client.task.MessageReceiver;
import ait.socket.client.task.MessageSender;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketAppl {
    public static void main(String[] args) {
        if (args.length == 1) {
            args = new String[]{args[0], "9000"};
        }
        if (args.length == 0) {
            args = new String[]{"127.0.0.1", "9000"};
        }
        String serverIp = args[0];
        int serverPort = Integer.parseInt(args[1]);
        try {
            Socket socket = new Socket(serverIp, serverPort);
            Thread receiver = new Thread(new MessageReceiver(socket));
            receiver.setDaemon(true);
            receiver.start();
            Thread sender = new Thread(new MessageSender(socket));
            sender.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
