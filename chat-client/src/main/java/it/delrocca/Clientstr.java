package it.delrocca;

import java.io.*;
import java.net.*;

public class Clientstr {
    String NomeServer = "localhost"; // indirizzo server locale
    int SocketServer = 6789; // porta per servizio

    String nome;
    Thread l;
    Socket miosocket;
    BufferedReader tastiera; // buffer per l'input da tastiera
    String StringaUtente; // stringa inserita da utente
    String StringaRicevutaDalServer; // stringa inserita dal server
    DataOutputStream outVersoServer; // stream di output
    BufferedReader inDalServer; // stream di input

    public String Accesso(Socket socket) throws Exception {
        do {
            System.out.println("Scrivi Username ");
            String nome = tastiera.readLine();
            outVersoServer.writeBytes(nome+'\n');
            
            StringaRicevutaDalServer = inDalServer.readLine();
            System.out.println(StringaRicevutaDalServer);
        } while (StringaRicevutaDalServer.equals("Username ripetuto"));
        l.start();
        comunica();

        return nome;
    }

    public Socket connetti() throws Exception {




        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(NomeServer, SocketServer);
            
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());

            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            l = new Thread(new listener(inDalServer));
            
            Accesso(miosocket);
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }

    public void comunica() {
        for (;;) {
            try {
                StringaUtente = tastiera.readLine();
                String[] controllo = new String[0];
                if (StringaUtente.indexOf("P") == 0) {
                    controllo = StringaUtente.split(":", 3);
                    if (controllo.length == 3) {
                        outVersoServer.writeBytes(StringaUtente + '\n');
                    } else {
                        System.out.println("sintassi errata");
                    }
                }
                if (StringaUtente.indexOf("A") == 0) {
                    controllo = StringaUtente.split(":", 2);
                    if (controllo.length == 2) {
                        outVersoServer.writeBytes(StringaUtente + '\n');
                    } else {
                        System.out.println("sintassi errata");
                    }
                }

                if (StringaUtente.equals("CHIUDI")) {
                    outVersoServer.writeBytes(StringaUtente + '\n');
                    miosocket.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione col server");
                System.exit(1);
            }
        }

    }
}
