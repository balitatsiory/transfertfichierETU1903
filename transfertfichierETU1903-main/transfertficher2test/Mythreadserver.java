package mythreadserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import myserver.Myserver;

public class Mythreadserver implements Runnable {
    Myserver myserver;
    StringBuilder path;
    byte[] fileContentBytes;
    public byte[] getFileContentBytes() {
        return fileContentBytes;
    }

    public void setFileContentBytes(byte[] fileContentBytes) {
        this.fileContentBytes = fileContentBytes;
    }

    public Mythreadserver(Myserver myserver,StringBuilder path) {
        this.myserver=myserver;
        this.path=path;

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            //maka ny ANARAN ilay fichier omena azy
            System.out.println("okkkkkkkkkk");
            ObjectInputStream obj=new ObjectInputStream(this.getMyserver().getSocket().getInputStream());
            String filetodownload=(String)obj.readObject();
            System.out.println("okkkkkkkkkkbeeeeeeeeee");
            System.out.println(filetodownload);

            // System.out.println(this.getSocket().isClosed()+"1");
            //mandefa fichier nilainy
            String[] nomfichierserversecondaire={"sc1file","sc2file","sc3file"};

            //mandefa any @ client ireo fichier anaty nomfichierserversecondaire

            for (int i = 0; i < this.getMyserver().getSocketsecondaire().length; i++) {
                String stringBuilder=this.getPath().toString()+"//"+nomfichierserversecondaire[i]+"//"+filetodownload;
                System.out.println(stringBuilder);
                File file=new File(stringBuilder);
                FileInputStream fileInputStream = new FileInputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(this.getMyserver().getSocket().getOutputStream());
                // System.out.println(this.getSocket().isClosed()+"2");
                String filename = file.getName();
                byte[] fileNameBytes = filename.getBytes();
                byte[] fileContentBytes = new byte[(int)file.length()];
                fileInputStream.read(fileContentBytes);

                dataOutputStream.writeInt(fileNameBytes.length);
                System.out.println(fileNameBytes.length);
                dataOutputStream.write(fileNameBytes);

                dataOutputStream.writeInt(fileContentBytes.length);
                dataOutputStream.write(fileContentBytes);
                dataOutputStream.flush();
                System.out.println("lasa");
            }

        } catch (Exception l) {
            // TODO: handle exception
            l.printStackTrace();
        }
    }


    public StringBuilder getPath() {
        return path;
    }

    public void setPath(StringBuilder path) {
        this.path = path;
    }
    public Myserver getMyserver() {
        return myserver;
    }

    public void setMyserver(Myserver myserver) {
        this.myserver = myserver;
    }


    }
