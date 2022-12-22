package mythreadserversecondaire;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class Mythreadserversecondaire implements Runnable {
    Socket socket1;

    public Mythreadserversecondaire(Socket socket1) {
        this.socket1=socket1;        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            ///MANDRAY
            DataInputStream dataInputStream = new DataInputStream(this.getSocket1().getInputStream());
            int fileNameLength = dataInputStream.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes,0,fileNameBytes.length);
            String filename = new String(fileNameBytes);
            File fileToDownload = new File(filename);
            int fileContentLength = dataInputStream.readInt();

            byte[] fileContentBytes = new byte[fileContentLength];
            dataInputStream.readFully(fileContentBytes,0,fileContentLength);

            ///MANORATRA
                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                fileOutputStream.write(fileContentBytes);
                fileOutputStream.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public Socket getSocket1() {
        return socket1;
    }

    public void setSocket1(Socket socket1) {
        this.socket1 = socket1;
    }

}
