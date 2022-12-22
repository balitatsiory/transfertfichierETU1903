package myserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import frame.Frame;
import mythreadserver.*;
import mythreadserversecondaire.Mythreadserversecondaire;
import server.*;
import threadsocket.Threadsocket;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
public class Myserver {
    Socket socket;
    Socket[] socketsecondaire;
    String option;

    public Myserver() {
    try {
            
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket[] socketsecondaire=new Socket[3];
        for (int i = 0; i < socketsecondaire.length; i++) {
            socketsecondaire[i]=new Socket("localhost",1235+i);
        }
        this.socketsecondaire=socketsecondaire;

        int fileId = 0;

        JPanel jPanel = new JPanel();

        Frame frame = new Frame();

        this.setOption(new String());

        boolean first=true;
        while (true) {

            Socket so=serverSocket.accept();
            System.out.println("new socket");

            Threadsocket threadsocket=new Threadsocket(this,so,first);
            Thread recevoir = new Thread(threadsocket);
            recevoir.start();
            first=false;
        }

    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }


    }

    public static String getFileExtension(String fileName){
        int i = fileName.lastIndexOf('.');
        if (i > 0){
            return fileName.substring(i+1);
        }else {
            return "extension invalide.";
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void getfirstoption() {
        String option=new String();
        try {
            ObjectInputStream obj1=new ObjectInputStream(this.getSocket().getInputStream());
            option=(String)obj1.readObject();
            System.out.println("option _ "+option);
            // this.getSocket().close();
            } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        this.setOption(option);

        return;
    }
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Socket[] getSocketsecondaire() {
        return socketsecondaire;
    }

    public void setSocketsecondaire(Socket[] socketsecondaire) {
        this.socketsecondaire = socketsecondaire;
    }

}
