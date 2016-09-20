package com.clouway.echoclient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        try {
            Client echoClient = new Client(InetAddress.getLocalHost(), 6000);
            echoClient.connect();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}