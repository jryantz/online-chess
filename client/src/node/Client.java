package node;

import java.io.*;
import java.net.Socket;

public class Client {

    String fromClient;

    /**
     * Prepares the client for execution.
     */
    public Client() {

        try {
            Socket s = new Socket("127.0.0.1", 4000);

            // Sending to server.
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            //output.writeBytes("Test...\n");
            output.writeBytes("--move id piece A1 A2\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true) {
                if((fromClient = in.readLine()) != null) {
                    System.out.println(fromClient);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}