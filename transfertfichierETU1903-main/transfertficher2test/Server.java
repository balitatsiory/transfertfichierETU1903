package server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import frame.Frame;
import myserver.Myserver;

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
public class Server {

    public static void main(String[] args) throws Exception {
        Myserver myServer=new Myserver();
    }

}
