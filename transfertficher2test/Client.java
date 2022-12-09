package client;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import frame.Frame;

import java.awt.*;
import java.io.File;
import java.net.Socket;

import panelclient.Panelclient;

public class Client {
    public static void main(String[] args) {
        File[] fileToSend = new File[1];
        
        Panelclient panelclient = new Panelclient();
try {
        Socket socket = new Socket("localhost",1234);
        Frame jFrame=new Frame(panelclient,socket);

} catch (Exception e) {
    // TODO: handle exception
    e.printStackTrace();
}
    }
}
