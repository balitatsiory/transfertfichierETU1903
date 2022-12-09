package listenerchoisefile;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;

import clientlistener.Clientlistener;
public class Listenerchoisefile implements MouseListener{
    Clientlistener clientlistener;
    Socket socket;


    public Listenerchoisefile(Clientlistener clientlistener,Socket socket) {
    this.clientlistener=clientlistener;
    this.socket=socket;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        JLabel labelfilename=(JLabel)e.getSource();
        System.out.println(labelfilename.getText());

        String filename=labelfilename.getText();
        try {
            ObjectOutputStream obj=new ObjectOutputStream(this.getSocket().getOutputStream());
            obj.writeObject(filename);
            obj.flush();
            } catch (Exception f) {
            // TODO: handle exception
            f.printStackTrace();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    public Clientlistener getClientlistener() {
        return clientlistener;
    }

    public void setClientlistener(Clientlistener clientlistener) {
        this.clientlistener = clientlistener;
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
