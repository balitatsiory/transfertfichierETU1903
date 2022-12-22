package frame;

import java.awt.BorderLayout;
import java.net.Socket;

import javax.swing.*;

import panelclient.Panelclient;


public class Frame extends JFrame {

    public Frame() {
        this.setTitle("Serveur pour se connecter");
        this.setSize(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public Frame(Panelclient panelclient2,Socket socket) {
        Panelclient panelclient = new Panelclient(this,socket);

        this.add(panelclient);
        
        this.setTitle("Client");
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    
    
}
