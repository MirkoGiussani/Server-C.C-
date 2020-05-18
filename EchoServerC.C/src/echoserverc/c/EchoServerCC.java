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
                    String responseSwitch = "";
                    String nome = "";
                    String valore = "";
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

                    System.out.print("connection received: [" + client.getInetAddress().getHostAddress() + "] ");
                    response = in.readLine();   //metto in ascolto il server
                    responseSwitch = response.substring(0, response.indexOf(":")); 
                    String [] separatore = response.split(":");
                    switch(responseSwitch){
                        case "LOG":  //     LOG: name: passw
                            String nomeUtente = separatore[1];
                            String passw = separatore[2];
                            out.println(response.toUpperCase());  //prova per mandare un messaggio dal server al client
                            out.flush();
                            break;
                        case "CA": //       CA: nome: cognome: telefono: CFiscale
                            nome = separatore[1];
                            String cognome = separatore[2];
                            String NumTelefono = separatore[3];
                            String CodFiscale = separatore[4];
                            break;
                        case "PREL":
                            valore = separatore[1];
                            
                            break;
                        case "VERS":
                            valore = separatore[1];
                            
                            break;
                        case "GC":
                            String ibanDebitore = separatore[1];
                            String ibanCreditore = separatore[2];
                            valore = separatore[3];
                            
                            break;
                        case "SALDO":
                            //ritorna il saldo
                            break;
                        case "LMOV":
                            //10 movimenti
                            break;
                        case "ANG":
                            //nome, cognome, mail
                            break;
                        case "BON":
                            nome = separatore[1];
                            valore = separatore[2];
                            break;
                    }
                    
                    System.out.println("string received = " + response);    //scrive sulla socket
                    out.write(response.toUpperCase()); 
                    response = in.readLine();

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
