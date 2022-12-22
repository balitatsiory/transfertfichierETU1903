package sc2;
import java.net.ServerSocket;
import java.net.Socket;
import mythreadsecondaire.Mythreadsecondaire;

public class Sc2 {


    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1236);
            System.out.println("serveur secondaire sc2 creer");
            Socket so=serverSocket.accept();
            String filename="sc2file";

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
