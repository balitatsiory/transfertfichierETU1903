package mythreadsecondaire;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Mythreadsecondaire implements Runnable  {
    Socket socket;
    String filedirectory;

    public Mythreadsecondaire(Socket socket,String filedirectory) {
        this.socket = socket;
        this.filedirectory=filedirectory;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        //maka ny fichier nomena
        try {
            DataInputStream dataInputStream = new DataInputStream(this.getSocket().getInputStream());
            int fileNameLength = dataInputStream.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            System.out.println("name tonga");
            dataInputStream.read(fileNameBytes);
            String filename = new String(fileNameBytes);
            System.out.println(filename);
            File fileToDownload = new File("./sauvegarde/"+this.filedirectory+"/"+filename);
            int fileContentLength = dataInputStream.readInt();
            System.out.println(fileContentLength);

            byte[] fileContentBytes = new byte[fileContentLength];
            dataInputStream.read(fileContentBytes);
            System.out.println("ok111");

                //MAMETRAKA ILAY FICHIER AZO ANATY REPERTOIRE
                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload,true);
                System.out.println("ok");
                fileOutputStream.write(fileContentBytes);
                dataInputStream.close();
                fileOutputStream.close();
                System.out.println("tongaaaaa");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


}

