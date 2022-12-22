package clientlistener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ColorUIResource;

import frame.Frame;
import listenerchoisefile.Listenerchoisefile;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import panelclient.Panelclient;
import mythreadclient.*;

public class Clientlistener implements MouseInputListener {
    File[] fileTosend;
    String jlFilename;
    Panelclient panelclient;
    Socket socket;

    public Clientlistener(File[] fileTosend,Panelclient panelclient,Socket socket) {
        this.fileTosend = fileTosend;
        this.panelclient=panelclient;
        this.socket=socket;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        try {
                JButton button =(JButton)e.getSource();

                ObjectOutputStream obj1=new ObjectOutputStream(this.getSocket().getOutputStream());
                String option=new String();


        if (button.getText().compareToIgnoreCase("Download")==0) {
            option="Download";
            obj1.writeObject(option);
            //obj1.flush();

            ///maka liste nom fichiers azo alaina
            ObjectInputStream obj=new ObjectInputStream(this.getSocket().getInputStream());
            String[] filesinside=(String[])obj.readObject();

            JFrame jFrame=new JFrame();
            jFrame.setSize(300,300);
            jFrame.setVisible(true);
            JPanel jPanel = new JPanel();
            JScrollPane jScrollPane = new JScrollPane(jPanel);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            Box vertical=Box.createVerticalBox();
            vertical.add(new JLabel("liste des fichiers pouvant etre telecharge"));
            //mandefa ny anaran ilay fichier ilaina
            Listenerchoisefile listener = new Listenerchoisefile(this,this.getSocket());
            for (int i = 0; i < filesinside.length; i++) {
                JLabel jlFileName = new JLabel(filesinside[i]);
                jlFileName.setForeground(new ColorUIResource(0, 0, 200));
                jlFileName.addMouseListener(listener);
                vertical.add(jlFileName);
            }
            jPanel.add(vertical);
            jFrame.add(jPanel);


            JOptionPane.setRootFrame(jFrame);

            try {
                Mythreadclient mythread=new Mythreadclient(this.getSocket());
                Thread recevoir=new Thread(mythread);
                recevoir.start();
            } catch (Exception o) {
                // TODO: handle exception
                o.printStackTrace();
            }
     System.out.println("yyyy");

        } else if (button.getText().compareToIgnoreCase("Upload")==0) {
            option="Upload";
            obj1.writeObject(option);
            obj1.flush();

            ///choisi im fichier a envoye
            JFileChooser jFileChooser = new JFileChooser(); ///ouvre un explorateur de fichier
            jFileChooser.setDialogTitle("Choisissez le fichier a envoyé");
            if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                fileTosend[0] = jFileChooser.getSelectedFile();
                jlFilename="le fichier selectionne est: "+fileTosend[0].getName();
                this.getPanelclient().getTxt2().setText(jlFilename);
                this.getPanelclient().setSelected(true);
                JButton send = new JButton("Envoyer");
                this.getPanelclient().add(send);
                send.addMouseListener(this);
            }
            
        }
        else if (button.getText().compareToIgnoreCase("Envoyer")==0) {
            ///SOCKET :
            try {
                FileInputStream fileInputStream = new FileInputStream(fileTosend[0].getAbsolutePath());
                 //socket = new Socket("localhost",1234);

                DataOutputStream dataOutputStream = new DataOutputStream(this.getSocket().getOutputStream());

                String filename = fileTosend[0].getName();
                byte[] fileNameBytes = filename.getBytes();

                byte[] fileContentBytes = new byte[(int)fileTosend[0].length()];
                fileInputStream.read(fileContentBytes);

                dataOutputStream.writeInt(fileNameBytes.length);
                System.out.println(fileNameBytes.length);
                dataOutputStream.write(fileNameBytes);

                dataOutputStream.writeInt(fileContentBytes.length);
                dataOutputStream.write(fileContentBytes);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (button.getText().compareToIgnoreCase("Telecharger")==0) {

        }
    } catch (Exception h) {
        // TODO: handle exception
        System.out.println(h.getMessage());
        h.printStackTrace();
    }


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public File[] getFileTosend() {
        return fileTosend;
    }

    public void setFileTosend(File[] fileTosend) {
        this.fileTosend = fileTosend;
    }
    public Panelclient getPanelclient() {
        return panelclient;
    }

    public void setPanelclient(Panelclient panelclient) {
        this.panelclient = panelclient;
    }

    public String getJlFilename() {
        return jlFilename;
    }

    public void setJlFilename(String jlFilename) {
        this.jlFilename = jlFilename;
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    
}
