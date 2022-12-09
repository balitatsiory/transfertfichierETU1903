package client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ListenerChoose implements ActionListener {
    File[] fileTosend;
    JLabel jlFilename;
    public ListenerChoose(File[] f, JLabel jl){
        this.fileTosend = f;
        this.jlFilename = jl;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choisissez le fichier a envoyé");

        if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            fileTosend[0] = jFileChooser.getSelectedFile();
            jlFilename.setText("le fichier selectionne est: "+fileTosend[0].getName());
        }
    }
}
