package sc1;
import java.net.ServerSocket;
import java.net.Socket;

import mythreadsecondaire.Mythreadsecondaire;

public class Sc1 {


    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1235);
            System.out.println("serveur secondaire sc1 creer");
            Socket so=serverSocket.accept();

            String filename="sc1file";

            Mythreadsecondaire mythreadsecondaire=new Mythreadsecondaire(so,filename);
            Thread recevoir = new Thread(mythreadsecondaire);
            recevoir.start();


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    
}
