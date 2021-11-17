package it.delrocca;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Clientstr cliente =new Clientstr();
        try {
            cliente.connetti();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
