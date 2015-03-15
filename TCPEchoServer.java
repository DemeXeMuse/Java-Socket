/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.budimansoft.socket.contohlain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Imbar
 */
public class TCPEchoServer {

    private static ServerSocket servSock;
    private static final int PORT = 8088;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Opening port...\n");
        try {
            servSock = new ServerSocket(PORT); 
        } catch (IOException ioEx) {
            System.out.println("Unable to attach to port! " + ioEx.getMessage());
            System.exit(1);
        }
        
        do {
            handleClient();
        } while(true);
        
    }
    
    private static void handleClient() {
        Socket client = null;
        try {
            client = servSock.accept();
            Scanner input = new Scanner(client.getInputStream());
            PrintWriter outToClinet = new PrintWriter(client.getOutputStream(), true);
            int numMessages = 0;
            String message = input.nextLine();
            
            while(!message.equals("keluar ah")) {
                System.out.println("Klient interaksi");
                numMessages++;
                outToClinet.println(numMessages + " Dari Imbar-Pc : Apanya yang \"" + message + "\"?");
                message = input.nextLine();                
            }
            
            outToClinet.println("\nDari Imbar-Pc : BODO EMANG GW PIKIRIN!");
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n* Closing connection... *");
                servSock.close();       
                System.exit(0);
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }   // handleClient() end
    
    
}
