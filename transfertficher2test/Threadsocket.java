package threadsocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;

import myserver.Myserver;
import mythreadserver.*;

public class Threadsocket implements Runnable {
    Myserver myserver;
    Socket socketentrant;
    boolean first;
    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Threadsocket(Myserver myserver,Socket socketentrant,boolean first) {
        this.setMyserver(myserver);
        this.getMyserver().setSocket(socketentrant);
        this.first=first;

    }

    @Override
    public void run() {
        
       // while (true) {
            
            if (this.first==true) {
                System.out.println("CLient principal entree");
                this.getMyserver().getfirstoption();
                this.setFirst(false);
                }

        // TODO Auto-generated method stub
        try{
        if (myserver.getOption().compareToIgnoreCase("Download")==0) {
            System.out.println("Myserver.Myserver()");
            //mandefa list nom fichier azo alaina
            StringBuilder path= new StringBuilder("D://L2//Naina//dataTransfer//src//transfertficher2test//sauvegarde");
            String path2=path.toString()+"//sc1file";
            File file=new File(path2);
            String[] filesinside=file.list();
            System.out.println(filesinside[0]);
            ObjectOutputStream obj=new ObjectOutputStream(this.myserver.getSocket().getOutputStream());
            obj.writeObject(filesinside);
            //obj.close();
            obj.flush();
            

                            try {
            //maka ny ANARAN ilay fichier omena azy
            //mandefa fichier nilainy
                                Mythreadserver mythread=new Mythreadserver(this.myserver,path);
                                Thread recevoir = new Thread(mythread);
                                recevoir.start();

                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
            
        } else if (this.myserver.getOption().compareToIgnoreCase("Upload")==0){
                        try {
                            //recupere fichier sy mizara sy mandefa azy
                            boolean b=true;
                            while (b==true) {
                                byte[] by=new byte[4096];
                                DataInputStream dataInputStream = new DataInputStream(this.myserver.getSocket().getInputStream());
                                int fileNameLength = dataInputStream.readInt();
                                System.out.println(fileNameLength);
                                if (fileNameLength>0) {
                                    byte[] fileNameBytes = new byte[fileNameLength];
                                    dataInputStream.readFully(fileNameBytes,0,fileNameBytes.length);
                                    String filename = new String(fileNameBytes);
                                    System.out.println(" fichier a envoyer : "+filename);
                                    File fileToDownload = new File(filename);
                                    int fileContentLength = dataInputStream.readInt();
                                    System.out.println("taille = "+fileContentLength);
                        
                                    byte[] fileContentBytes = new byte[fileContentLength];
                                    dataInputStream.readFully(fileContentBytes,0,fileContentLength);
    
                                    int divide=fileContentLength/3;
                                    int limite=0;
    
                                    DataOutputStream dataOutputStream= null;
                                    for (int i = 0; i < this.getMyserver().getSocketsecondaire().length; i++) {

                                        dataOutputStream = new DataOutputStream(this.getMyserver().getSocketsecondaire()[i].getOutputStream());
                                        
                                        dataOutputStream.writeInt(fileNameBytes.length);
                                        dataOutputStream.write(fileNameBytes);
                                        if (i==this.getMyserver().getSocketsecondaire().length-1) {
                                            divide=(fileContentLength-limite);
                                        }
                                        System.out.println("De "+limite+" a "+divide);

                                        
                                        dataOutputStream.writeInt(divide);
                                        dataOutputStream.write(fileContentBytes,limite,divide);
                                        dataOutputStream.flush();
                                        limite=divide;
                                        divide=divide+divide;
                                        System.out.println("cva");
                                    }
                                    b=false;
                                }

                                
                            }
                    
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }


        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }

}

    public Myserver getMyserver() {
        return myserver;
    }

    public void setMyserver(Myserver myserver) {
        this.myserver = myserver;
    }

    public Socket getSocketentrant() {
        return socketentrant;
    }

    public void setSocketentrant(Socket socketentrant) {
        this.socketentrant = socketentrant;
    }
    
}
