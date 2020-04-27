/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserverc.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Mirko
 */
public class EchoServerCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000, 10);
            System.out.println("Server avviato...");
            Socket client = null;
            while (true) {
                try {
                    client = server.accept();

                    int c;
                    String response = "";
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

                    System.out.print("connection received: [" + client.getInetAddress().getHostAddress() + "] ");
                    response = in.readLine();
                    
                    while (!(response.equals("exit"))) {
                        System.out.println("string received = " + response);    //scrive sulla 
                        out.write(response.toUpperCase());  //
                        response = in.readLine();
                    }
                    System.out.println("exit for while");
                    client.close();
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (client != null) {
                            client.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
    }
    

