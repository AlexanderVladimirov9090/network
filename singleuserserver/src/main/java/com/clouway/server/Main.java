package com.clouway.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Main{
    public static void main(String[] args) {
        try {
            EchoServer echoServer = new EchoServer(new ServerSocket(6000));
            System.out.println("Server Running.");
            echoServer.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}