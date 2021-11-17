package server;
import java.util.HashMap;

import server.ServerThread;

public class AppClients {
    HashMap <String,ServerThread> A= new HashMap<>();
    public AppClients(){

    }
    public boolean AggiungiNome(String Nome,ServerThread Server){
        if(A.containsKey(Nome)){
          return false;
        }
        A.put(Nome,Server);

        if(A.size()==1){
            A.get(Nome).invia(a());
        }
        for(String i : A.keySet()){
            A.get(i).invia(Lista());
        }
      
        return true;
    } 
    public boolean Privato(String stringa ){
        String[] messaggio=new String[0];
        messaggio=stringa.split(":",3);
        A.get(messaggio[1]).invia(messaggio[2]);
        return true;
    } 
    public boolean Pubblico(String stringa,String Nome){
        String[] messaggio=new String[0];
        messaggio=stringa.split(":",2);   
        for(String i:A.keySet()){
            if(i.equals(Nome)){
                continue;
            }
        A.get(i).invia(Nome + " : "+messaggio[1]);
        }
        return true;
    
    } 
    public String Lista(){
        String listaclient="NOMI:";

        for(String i : A.keySet()){
            listaclient+=i+":";
        }
        return listaclient;
    }
    public String a(){
        String listaclient="Sei l'unico ";
        return listaclient;
    }
}
