package com.clouway.echoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
class Client {
    private final InetAddress inetAddress;
    private final int port;

    Client(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }


    void connect() {
        try (Socket clientSocket = new Socket(inetAddress, port)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            listen(out, bufferedReader, scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(PrintWriter out, BufferedReader bufferedReader, Scanner scanner) {
        String response;
        try {
            while ((response = bufferedReader.readLine()) != null) {
                System.out.println("Server Response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}