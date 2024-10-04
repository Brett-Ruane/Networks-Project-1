package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class myftpserver {

    protected static String server_IP;

    public static void main(String[] args) throws Exception {

        Socket socket = null;
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        Integer port = 0;

        try {
            port = Integer.valueOf(args[0]); // grab port from command line arg
        } catch (NumberFormatException e) {
            System.out.println("Invalid Port");
            System.exit(0);
        } // catch

        try {
            InetAddress iAddress = InetAddress.getLocalHost();
            server_IP = iAddress.getHostAddress();
            System.out.println("Server IP address : " + server_IP);
        } catch (UnknownHostException e) {
        }

        ServerSocket server = new ServerSocket(port);
        String serverName = "Bob Smith";
        System.out.println("server running on ip: " + server.getInetAddress());
        System.out.println("server is now online running on port: " + port);
        Socket s = server.accept(); // waits for connection from client
        System.out.println("Client Connected");
        reader = new InputStreamReader(s.getInputStream());
        writer = new OutputStreamWriter(s.getOutputStream());
        br = new BufferedReader(reader);
        bw = new BufferedWriter(writer);

        String y = br.readLine();
        int x = Integer.parseInt(y);

        String clientName = br.readLine();

        System.out.println("Client of " + clientName + " Server of " + serverName);

        int z = (int) (Math.random() * 100) + 1;

        int a = z + x;

        bw.write("Server of " + serverName + " Server Int = " + z + ", Sum of both = " + a + "\n");

        while (true) {

            Thread.sleep(1000);
            bw.write(">");
            bw.newLine();
            bw.flush();

            String msgFromClient = br.readLine();
            String arr[] = msgFromClient.split(" ");

            System.out.println("The command is " + msgFromClient);

            if (arr[0].equals("quit")) {
                bw.write("Closing connection");
                bw.newLine();
                bw.flush();
                s = server.accept(); // waits for connection from client
                System.out.println("Client Connected");
                reader = new InputStreamReader(s.getInputStream());
                writer = new OutputStreamWriter(s.getOutputStream());
                br = new BufferedReader(reader);
                bw = new BufferedWriter(writer);
            } // if
        } // while

    } // main
} // myftpserver
