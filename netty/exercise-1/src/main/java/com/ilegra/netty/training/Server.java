package com.ilegra.netty.training;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {
        int port = (args.length == 0) ? 7001 : Integer.valueOf(args[0]);

        new Server().start(port);
    }

    public void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Server started");

            int current = 0;
            while (true) {
                // to avoid infinity loop
                if (current++ >= Integer.MAX_VALUE) {
                    break;
                }

                System.out.println("Waiting for a client ...");
                try {
                    Socket client = server.accept();
                    System.out.println("Client accepted");
                    //new Thread(() -> {
                    executorService.submit(() -> {
                        try {
                            System.out.println("Thread Name: " + Thread.currentThread().getName());

                            DataInputStream in = new DataInputStream(
                                    new BufferedInputStream(client.getInputStream()));

                            String data = in.readUTF();
                            String[] numbers = data.split("-");
                            int result = Service.sum(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));

                            OutputStream outputStream = client.getOutputStream();
                            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                            dataOutputStream.write(String.valueOf(result).getBytes());
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
