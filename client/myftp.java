package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class 
public class myftp {

    public static void main(String[] args) throws IOException {

        Integer port = 0;
        String machineName = "";
        String clientName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type number from 1 - 100 (Integer)");
        int x = scanner.nextInt();

        try {
            machineName = args[0];
            port = Integer.valueOf(args[1]);
            clientName = args[2];
        } catch (NumberFormatException e) {
            System.out.println("Bad arguments");
            System.exit(0);
        } // catch

        Socket client = new Socket(machineName, port);

        InputStreamReader reader = new InputStreamReader(client.getInputStream());
        OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());

        BufferedReader br = new BufferedReader(reader);
        BufferedWriter bw = new BufferedWriter(writer);

        bw.write(Integer.toString(x));
        bw.newLine();
        bw.flush();
        bw.write(clientName);
        bw.newLine();
        bw.flush();

        while (true) {

            String serverMsg = br.readLine();
            System.out.print(serverMsg);
            String command = scanner.nextLine();

            bw.write(command);
            bw.newLine();
            bw.flush();

            String arr[] = command.split(" ");

            if (arr[0].equals("quit")) {
                serverMsg = br.readLine();
                System.out.println(serverMsg);
                break;
            } // if
            System.out.println("");
        } // while
        client.close();
    } // main
}
