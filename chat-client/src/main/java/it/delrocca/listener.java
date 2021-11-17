package it.delrocca;

import java.io.BufferedReader;
import java.io.IOException;

public class listener implements Runnable{
    BufferedReader inDalServer; // stream di input
    String messaggio;
    public listener(BufferedReader inDalServer){
        this.inDalServer=inDalServer;
    }
    

    public void run(){
        for(;;){
            try {
                messaggio = inDalServer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(messaggio);
        }
    }
}
