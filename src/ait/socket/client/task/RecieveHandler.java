package ait.socket.client.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecieveHandler implements Runnable {
    private final Socket socket;

    public RecieveHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try  {
            InputStream inputStream = socket.getInputStream();
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                String response = socketReader.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
