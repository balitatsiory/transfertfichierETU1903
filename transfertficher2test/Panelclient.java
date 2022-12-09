package panelclient;
import java.io.File;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import clientlistener.Clientlistener;
import frame.Frame;

public class Panelclient extends JPanel{
    File[] fileTosend;
    JLabel txt2;
    Boolean selected=false;
    Frame frame;
    public Panelclient() {
    }
    
    public Panelclient(Frame frame,Socket socket) {
        this.frame=frame;
        fileTosend=new File[1];
        txt2 = new JLabel();
        Box vertical = Box.createVerticalBox();
        JLabel txt = new JLabel("Veuillez choisir une option");
        txt2 = new JLabel();



        Clientlistener clientlistener = new Clientlistener(fileTosend,this,socket);
        JButton download = new JButton("Download");
        download.addMouseListener(clientlistener);

        JButton upload = new JButton("Upload");
        upload.addMouseListener(clientlistener);
        vertical.add(txt2);

        vertical.add(txt);
        vertical.add(download);
        vertical.add(upload);

        this.add(vertical);

    }
    public Frame getFrame() {
        return frame;
    }
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public JLabel getTxt2() {
        return txt2;
    }

    public void setTxt2(JLabel txt2) {
        this.txt2 = txt2;
    }
    public Boolean getSelected() {
        return selected;
    }
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
