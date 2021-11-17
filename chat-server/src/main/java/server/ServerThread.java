package server;

import java.net.*;


import java.io.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    AppClients m;
    String nome;

    public ServerThread(Socket socket, ServerSocket server, AppClients m) {
        this.client = socket;
        this.server = server;
        this.m = m;
    }

    public void invia(String messaggio) {
        try {
            outVersoClient.writeBytes(messaggio + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        boolean b = true;
        do {
            nome = inDalClient.readLine();
            b = m.AggiungiNome(nome, this);
            if (b == false) {
                invia("Username ripetuto");
            } else {
                invia("aggiunto con successo");
            }
        } while (b == false);

        for (;;) {
            stringaRicevuta = inDalClient.readLine();

            if (stringaRicevuta.indexOf("P") == 0) {
                m.Privato(stringaRicevuta);
            }
            if (stringaRicevuta.indexOf("A") == 0) {
                m.Pubblico(stringaRicevuta,nome);
            }
            if (stringaRicevuta.equals("CHIUDI")) {
                outVersoClient.writeBytes(stringaRicevuta + "(-->socket in chiusura)" + '\n');
                System.out.println("Echo sul server in chiusura   :" + stringaRicevuta);
                outVersoClient.close();
                inDalClient.close();
                client.close();
                break;
            }

        }
    }
}
